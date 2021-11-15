public class B {
    int seq;
    public B(){
        // initialization of the state of B
        this.seq = 0;
    }
    public void B_input(simulator sim,packet pkt){
        // process the packet recieved from the layer 3

        // verify checksum
        if(pkt.checksum != pkt.get_checksum()) return;

        if(pkt.seqnum == this.seq) {
            // send payload to layer five
            sim.to_layer_five('B', pkt.payload);
            
            // send ACK
            pkt.send_ack(sim, 'B', pkt.acknum);

            // increment sequence number
            this.seq++;
        }
    }
    public void B_output(simulator sim){

    }
    public void B_handle_timer(simulator sim){

    }
}
