public class A {
    public A(){
        // TODO: initialization of the states of A
        // seq
        // estimated rtt
        // ....
    }
    public void A_input(simulator sim, packet p){
        // TODO: recive data from the other side
        // process the ACK, NACK from B

    }
    public void A_output(simulator sim, msg m){
        // TODO: called from layer 5, pass the data to the other side
        packet p = new packet(0,0, m);
        sim.to_layer_three('A', p);
    }
    public void A_handle_timer(simulator sim){
        // TODO: handler for time interrupt
        // resend the packet as needed
    }
}
