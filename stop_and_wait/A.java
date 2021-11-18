public class A {
    String state;
    int seq;
    int estimated_rtt;
    packet lastpacket;
    
    public A(){
        // initialization of the states of A
        this.seq = 0; // initialize the first sequence number
        this.state = "WAIT_LAYER5"; // current state of A
        this.estimated_rtt = 30; // estimated rtt time for each packet
    }
    public void A_input(simulator sim, packet p){
        // receive data from the other side
        // process the ACK, NACK from B

        // ensure packet is not corrupted
        if(p.checksum != p.get_checksum());
        else if(lastpacket.acknum != p.acknum) { // incorrect ACK received
            // restart timer
            sim.envlist.remove_timer();
            sim.envlist.start_timer('A',(float)estimated_rtt);
            
             // resend last packet
            sim.to_layer_three('A', lastpacket);

            // reset state
            state = "WAIT_ACK";
        }
        else { // correct ACK received
            // reset timer
            sim.envlist.remove_timer();
            
            // reset state
            state = "WAIT_LAYER5";
        }
    }
    public void A_output(simulator sim, msg m){
        // called from layer 5, pass the data to the other side
        
        // if state is still WAIT_ACK it means ACK has not come back so don't send new data
        if(state.equals("WAIT_ACK")) return;
        
        // set up new packet
        packet p = new packet(this.seq, this.seq, m);
        this.seq = this.seq == 1 ? 0 : 1;
        lastpacket = new packet(p);

        // start timer
        sim.envlist.start_timer('A',(float)estimated_rtt);

        // send packet to B
        sim.to_layer_three('A', p);

        // reset state
        state = "WAIT_ACK";
    }
    public void A_handle_timer(simulator sim){
        // handler for time interrupt
        
        // restart timer
        sim.envlist.start_timer('A',(float)estimated_rtt);
        
        // resend the packet
        sim.to_layer_three('A', lastpacket);

        // reset state
        state = "WAIT_ACK";
    }
}
