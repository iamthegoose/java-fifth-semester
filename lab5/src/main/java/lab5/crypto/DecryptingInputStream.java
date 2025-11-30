package lab5.crypto;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DecryptingInputStream extends FilterInputStream {

    private final int key;

    public DecryptingInputStream(InputStream in, int key) {
        super(in);
        this.key = key;
    }

    @Override
    public int read() throws IOException {
        int b = super.read();
        return (b == -1) ? -1 : b - key;
    }
}
