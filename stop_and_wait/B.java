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
        sim.to_layer_five('B',pkt.payload);
    }
    public void B_output(simulator sim){

    }
    public void B_handle_timer(simulator sim){

    }
}
