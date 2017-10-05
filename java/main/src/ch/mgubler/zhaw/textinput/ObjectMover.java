package ch.mgubler.zhaw.textinput;

import ch.mgubler.zhaw.objects.PaintableObject;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class ObjectMover {

    private static final KeyStroke UP_W = KeyStroke.fromString("w");
    private static final KeyStroke LEFT_A = KeyStroke.fromString("a");
    private static final KeyStroke DOWN_S = KeyStroke.fromString("s");
    private static final KeyStroke RIGHT_D = KeyStroke.fromString("d");

    private Terminal terminal;

    public ObjectMover(Terminal terminal, PaintableObject moveableObject) {
        this.terminal = terminal;
    }

    public void pollInput() throws IOException {
        KeyStroke input = terminal.pollInput();
        if(UP_W == input){
        }else if(LEFT_A == input){

        }else if(RIGHT_D == input){

        }else if(DOWN_S == input){

        }else{
            if(input != null){
                System.out.println("Pressed: "+input);
            }
        }

    }

    private void decideAction(KeyStroke input) {



    }

}
