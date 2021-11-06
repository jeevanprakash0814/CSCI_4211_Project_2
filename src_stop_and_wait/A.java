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
        // TODO: recive data from the other side
        // process the ACK, NACK from B
        if(lastpacket.get_checksum() != p.get_checksum()) {
            if(seq == p.seqnum) {
                sim.envlist.remove_timer();
                sim.envlist.start_timer('A',(float)estimated_rtt);
                sim.to_layer_three('A', lastpacket);
                state = "WAIT_ACK";
            }
        } else if(seq == p.seqnum) {
            sim.envlist.remove_timer();
            state = "WAIT_LAYER5";
        }
    }
    public void A_output(simulator sim, msg m){
        // TODO: called from layer 5, pass the data to the other side
        packet p = new packet(0, 0, m);
        lastpacket = new(p);
        sim.envlist.start_timer('A',(float)estimated_rtt);
        sim.to_layer_three('A', p);
        state = "WAIT_ACK";
    }
    public void A_handle_timer(simulator sim){
        // handler for time interrupt
        sim.envlist.remove_timer();
        sim.envlist.start_timer('A',(float)estimated_rtt);
        sim.to_layer_three('A', lastpacket);
        state = "WAIT_ACK";
        // resend the packet as needed
    }
}
