public class B {
    int seq;

    public B(){
        // initialization of the state of B
        this.seq = 0;
    }
    public void B_input(simulator sim,packet pkt){
        // TODO: process the packet recieved from the layer 3
        // verify checksum
        // send ACK
        if(pkt.checksum != pkt.get_checksum());
        else if(pkt.seqnum != this.seq) {
            pkt.send_ack(sim, 'B', pkt.acknum);
        }
        else {
            sim.to_layer_five('B',pkt.payload);
            pkt.send_ack(sim, 'B', pkt.acknum);
            this.seq = this.seq == 1 ? 0 : 1;
        }
    }
    public void B_output(simulator sim){

    }
    public void B_handle_timer(simulator sim){
        System.out.println("I am in B Handle Timer");
    }
}
