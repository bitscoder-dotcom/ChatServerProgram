import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/*
This thread is responsible for reading server's input and printing it to the
console.
It runs in an infinite loop until the client disconnects from the server.
 */
public class ReadThread extends Thread{
    private BufferedReader reader;
    private Socket socket;
    private ChatClient client;

    public ReadThread(Socket socket, ChatClient client) {
        this.socket = socket;
        this.client = client;

        try {
            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
        } catch (IOException ex) {
            System.out.println("Error getting input stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            try {
                String response = reader.readLine();
                System.out.println("\n" + response);

                //prints the username after displaying the server's message
                if (client.getUserName() != null) {
                    System.out.println("[" + client.getUserName() + "]: ");
                }
            } catch (IOException ex) {
                System.out.println("Error reading from server: " + ex.getMessage());
                ex.printStackTrace();
                break;
            }
        }
    }
}
/*
This is a Java class called "ReadThread" that reads incoming messages from a
socket connection. It creates an input stream from the socket's input stream,
and wraps it in a BufferedReader for more efficient reading. The run method
reads lines from the input stream in a loop, and prints them to the console
along with the client's username, if it's available. If there's an IOException,
it will print the error message, and the loop will be broken.
 */