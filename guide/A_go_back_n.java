public class A {
    int seq;
    int estimated_rtt;
    circular_buffer c_b;
    public A(){
        /* go back n, the initialization of A
        // You can set the estimated_rtt to be 30, which is used as a parameter when you call start_timer
        // initialize the initial sequence number to 0.
        // you need to initialize the circular buffer, using "new circular_buffer(int max)". max is the number of the packets that the buffer can hold.
         */
    }
    public void A_input(simulator sim, packet p){
        /* go back n, A_input
        // verify that the packet is not corrupted.
        // update the circular buffer according to the acknowledgement number using "pop()"
         */
    }
    public void A_output(simulator sim, msg m){
        /* go back n, A_output
        // if the buffer is full, just drop the packet.
        // construct the packet. Make sure that the sequence number is correct.
        // send the packet and save it to the circular buffer using "push()" of circular_buffer
        // Set the timer using "sim.envlist.start_timer", and the time should be set to estimated_rtt. Make sure that there is only one timer started in the event list.
         */
    }
    public void A_handle_timer(simulator sim){
        /* go back n, A_handle_timer
        // read all the sent packet that it is not acknowledged using "read_all()" of the circular buffer and resend them
        // If you need to resend packets, set a timer after that
         */
    }
}
