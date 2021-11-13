public class B {
    int seq;
    public B(){
        /* go back n, the initialization of Bz
        // The state only need to maintain the information of expected sequence number of packet
         */
    }
    public void B_input(simulator sim,packet p){
        /* go back n, B_input
        // you need to verify the checksum to make sure that packet isn't corrupted
        // If the packet is the right one, you need to pass to the fifth layer using "sim.to_layer_five(entity, payload)"
        // Send acknowledgement using "send_ack(sim, entity, seq)" of packet based on the correctness of received packet
        // If the packet is the correct one, in the last step, you need to update its state ( update the expected sequence number)
         */
    }
    public void B_output(simulator sim){

    }
    public void B_handle_timer(simulator sim){

    }
}
