package ch.mgubler.zhaw.objects;

import ch.mgubler.zhaw.move.Direction;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.Assert;
import org.junit.Test;

public class DirectionTest {

    @Test
    public void getDirectionByKey_up() throws Exception {
        Assert.assertEquals(Direction.UP, Direction.getDirectionByKey('w'));
    }

    @Test
    public void getDirectionByKey_left() throws Exception {
        Assert.assertEquals(Direction.LEFT, Direction.getDirectionByKey('a'));
    }

    @Test
    public void getDirectionByKey_down() throws Exception {
        Assert.assertEquals(Direction.DOWN, Direction.getDirectionByKey('s'));
    }

    @Test
    public void getDirectionByKey_right() throws Exception {
        Assert.assertEquals(Direction.RIGHT, Direction.getDirectionByKey('d'));
    }

    @Test
    public void getDirectionByKey_undef() throws Exception {
        Assert.assertEquals(Direction.UNDEF, Direction.getDirectionByKey('K'));
    }

    @Test
    public void getDirectionByKeyStroke_up() throws Exception {
        Assert.assertEquals(Direction.UP, Direction.getDirectionByKeyStroke(new KeyStroke('w', false, false)));
    }

    @Test
    public void getDirectionByKeyStroke_left() throws Exception {
        Assert.assertEquals(Direction.LEFT, Direction.getDirectionByKeyStroke(new KeyStroke('a', false, false)));
    }

    @Test
    public void getDirectionByKeyStroke_down() throws Exception {
        Assert.assertEquals(Direction.DOWN, Direction.getDirectionByKeyStroke(new KeyStroke('s', false, false)));
    }

    @Test
    public void getDirectionByKeyStroke_right() throws Exception {
        Assert.assertEquals(Direction.RIGHT, Direction.getDirectionByKeyStroke(new KeyStroke('d', false, false)));
    }

    @Test
    public void getDirectionByKeyStroke_undef() throws Exception {
        Assert.assertEquals(Direction.UNDEF, Direction.getDirectionByKeyStroke(new KeyStroke(KeyType.Backspace)));
    }

}