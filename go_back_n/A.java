public class A {
    int seq;
    int estimated_rtt;
    circular_buffer c_b;
    int window;
    int nextAcknum;
    int packetsSent;
    boolean timerSet;

    public A(){
        // initialization of the states of A
        this.seq = 0; // initialize the first sequence number
        this.estimated_rtt = 30; // estimated rtt time for each packet
        this.window = 8; // window size
        this.nextAcknum = 0; // next acknum that is espected by A
        this.packetsSent = 0; // current number of packets sent
        timerSet = false; // setting the current state of the timer
        this.c_b = new circular_buffer(50); // initializing the circular buffer
    }
    public void A_input(simulator sim, packet p){
        // TODO: recive data from the other side
        // process the ACK, NACK from B
        if(p.checksum != p.get_checksum()) return;

        int popNum = p.acknum-nextAcknum;
        if(popNum == 0) {
            c_b.pop();
            nextAcknum++;
            packetsSent--; // window opened by one
        }
        else {
            for(int i=0; i<=popNum; i++) {
                c_b.pop();
                nextAcknum++;
                packetsSent--; // window opened one more slot
            }
        }

        if(timerSet) sim.envlist.remove_timer();
        sim.envlist.start_timer('A',(float)estimated_rtt);
        timerSet = true;
    }
    public void A_output(simulator sim, msg m){
        // called from layer 5, pass the data to the other side
    
        // drop packet for now since buffer is full
        if(c_b.isfull()) {
            System.out.println("Buffer is full!");
            return;
        }
        // construct new packet
        packet p = new packet(this.seq,this.seq, m);

        // set packet number up
        this.seq++;

        // push packet onto buffer
        c_b.push(p);


        // only send packets over if there is space in the window
        if(packetsSent <= window) {
            packet[] pkts = c_b.read_all();
            
            // send packet to B
            sim.to_layer_three('A', pkts[0]);
            packetsSent++;

            // reset the timer
            if(timerSet) sim.envlist.remove_timer();
            sim.envlist.start_timer('A',(float)estimated_rtt);
            timerSet = true;
        }
    }
    public void A_handle_timer(simulator sim){
        // handler for time interrupt
        // read all the packets
        packet[] pkts = c_b.read_all();

        // resend all the packets
        int numSent = 0;
        if(pkts.length > window) numSent = window; // Only send over un-acked packets within the window size
        else numSent = pkts.length;
        for(int i=0; i<numSent; i++) {
            sim.to_layer_three('A', pkts[i]);
        }

        // set a timer here that is 8*estimated_rtt
        sim.envlist.start_timer('A',(float)window*estimated_rtt);
        timerSet = true;

    }
}
