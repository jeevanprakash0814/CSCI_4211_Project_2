public class B {
    public B(){
        // TODO: initialization of the state of B
        // this.seq
        // ...
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
