public class B {
    int seq;

    public B(){
        // initialization of the state of B
        this.seq = 0; // initialize the first expected sequence number
    }
    public void B_input(simulator sim,packet pkt){
        // process the packet received from the layer 3

        // verify checksum
        if(pkt.checksum != pkt.get_checksum());
        else if(pkt.seqnum != this.seq) { // incorrect packet received
            // send NACK
            pkt.send_ack(sim, 'B', pkt.acknum);
        } else { // correct packet received
            // send payload to layer 5
            sim.to_layer_five('B',pkt.payload);

            // send ack of received packet to A
            pkt.send_ack(sim, 'B', pkt.acknum);

            // alternate bit to for next expected sequence number
            this.seq = this.seq == 1 ? 0 : 1;
        }
    }
    public void B_output(simulator sim){

    }
    public void B_handle_timer(simulator sim){

    }
}
