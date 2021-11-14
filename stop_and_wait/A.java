public class A {
    String state;
    int seq;
    int estimated_rtt;
    packet lastpacket;
    
    public A(){
        // initialization of the states of A
        this.seq = 0;
        this.state = "WAIT_LAYER5";
        this.estimated_rtt = 30;
    }
    public void A_input(simulator sim, packet p){
        // receive data from the other side
        // process the ACK, NACK from B
        // what to do if the state is already WAIT_LAYER5
        // state should be WAIT_ACK for this method
        if(p.checksum != p.get_checksum()) {
            
        } else if(lastpacket.acknum != p.acknum) {
            sim.envlist.remove_timer();
            sim.envlist.start_timer('A',(float)estimated_rtt);
            sim.to_layer_three('A', lastpacket);
            state = "WAIT_ACK";
        } else {
            sim.envlist.remove_timer();
            state = "WAIT_LAYER5";
        }
    }
    public void A_output(simulator sim, msg m){
        // called from layer 5, pass the data to the other side
        // if state is still WAIT_ACK it means ACK has not come back so don't send new data
        if(state.equals("WAIT_ACK")) return;

        // sets up new packet
        packet p = new packet(this.seq, this.seq, m);
        this.seq = this.seq == 1 ? 0 : 1;
        lastpacket = new packet(p);

        // reset timer
        sim.envlist.start_timer('A',(float)estimated_rtt);

        // send packet to B
        sim.to_layer_three('A', p);

        // reset state
        state = "WAIT_ACK";
    }
    public void A_handle_timer(simulator sim){
        // handler for time interrupt
        sim.envlist.start_timer('A',(float)estimated_rtt);
        sim.to_layer_three('A', lastpacket);
        state = "WAIT_ACK";
        // resend the packet as needed
    }
}
