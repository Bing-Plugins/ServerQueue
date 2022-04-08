/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 */
package net.rfpixel.queue.ping;

import com.google.gson.Gson;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import net.rfpixel.queue.ping.ServerData;

public class Pinger {
    private final ServerListPing serverListPing;
    private ServerData serverData;
    private static final Gson gson = new Gson();

    public Pinger(String host, int port, int timeout) {
        this.serverListPing = new ServerListPing(new InetSocketAddress(host, port), timeout);
    }

    public boolean ping() {
        boolean isSuccess;
        block8: {
            try {
                try {
                    long start = System.currentTimeMillis();
                    if (this.serverListPing.connect()) {
                        ByteArrayOutputStream bs = new ByteArrayOutputStream();
                        DataOutputStream out = new DataOutputStream(bs);
                        out.write(0);
                        Pinger.writeVarInt(out, 4);
                        Pinger.writeString(out, this.serverListPing.getHost().getHostString());
                        out.writeShort(this.serverListPing.getHost().getPort());
                        Pinger.writeVarInt(out, 1);
                        this.sendPacket(bs.toByteArray());
                        this.sendPacket(new byte[1]);
                        Pinger.readVarInt(this.serverListPing.getDataInputStream());
                        int packetId = Pinger.readVarInt(this.serverListPing.getDataInputStream());
                        if (packetId != 0) {
                            throw new IOException("Invalid packetId");
                        }
                        int stringLength = Pinger.readVarInt(this.serverListPing.getDataInputStream());
                        if (stringLength < 1) {
                            throw new IOException("Invalid string length.");
                        }
                        byte[] responseData = new byte[stringLength];
                        this.serverListPing.getDataInputStream().readFully(responseData);
                        String jsonString = new String(responseData, StandardCharsets.UTF_8);
                        this.serverData = (ServerData)gson.fromJson(jsonString, ServerData.class);
                        this.serverData.setPing(System.currentTimeMillis() - start);
                        isSuccess = true;
                        break block8;
                    }
                    isSuccess = false;
                }
                catch (Exception e) {
                    isSuccess = false;
                    this.serverListPing.disconnect();
                }
            }
            finally {
                this.serverListPing.disconnect();
            }
        }
        return isSuccess;
    }

    public ServerData getServerData() {
        if (this.serverData == null) {
            this.serverData = new ServerData();
        }
        return this.serverData;
    }

    private void sendPacket(byte[] data) throws IOException {
        Pinger.writeVarInt(this.serverListPing.getDataOutputStream(), data.length);
        this.serverListPing.getDataOutputStream().write(data);
    }

    private static int readVarInt(DataInputStream in) throws IOException {
        byte k;
        int i = 0;
        int j = 0;
        do {
            k = in.readByte();
            i |= (k & 0x7F) << j++ * 7;
            if (j <= 5) continue;
            throw new RuntimeException("VarInt too big");
        } while ((k & 0x80) == 128);
        return i;
    }

    private static void writeVarInt(DataOutputStream out, int paramInt) throws IOException {
        while ((paramInt & 0xFFFFFF80) != 0) {
            out.write(paramInt & 0x7F | 0x80);
            paramInt >>>= 7;
        }
        out.write(paramInt);
    }

    private static void writeString(DataOutputStream out, String string) throws IOException {
        Pinger.writeVarInt(out, string.length());
        out.write(string.getBytes(Charset.forName("utf-8")));
    }

    public class ServerListPing {
        private final InetSocketAddress host;
        private Socket socket;
        private InputStream inputStream;
        private OutputStream outputStream;
        private int timeout;
        private DataInputStream dataInputStream;
        private DataOutputStream dataOutputStream;

        public ServerListPing(InetSocketAddress host, int timeout) {
            this.host = host;
            this.timeout = timeout;
        }

        public boolean connect() {
            boolean isSuccess;
            try {
                this.socket = new Socket();
                this.socket.setSoTimeout(this.timeout);
                this.socket.connect(this.host, this.timeout);
                this.inputStream = this.socket.getInputStream();
                this.dataInputStream = new DataInputStream(this.inputStream);
                this.outputStream = this.socket.getOutputStream();
                this.dataOutputStream = new DataOutputStream(this.outputStream);
                isSuccess = true;
            }
            catch (Exception e) {
                isSuccess = false;
            }
            return isSuccess;
        }

        public void disconnect() {
            try {
                if (this.dataInputStream != null) {
                    this.dataInputStream.close();
                }
                if (this.dataOutputStream != null) {
                    this.dataOutputStream.close();
                }
                if (this.socket != null && !this.socket.isClosed()) {
                    this.socket.close();
                }
            }
            catch (IOException iOException) {
                // empty catch block
            }
        }

        public InetSocketAddress getHost() {
            return this.host;
        }

        public Socket getSocket() {
            return this.socket;
        }

        public InputStream getInputStream() {
            return this.inputStream;
        }

        public OutputStream getOutputStream() {
            return this.outputStream;
        }

        public int getTimeout() {
            return this.timeout;
        }

        public DataInputStream getDataInputStream() {
            return this.dataInputStream;
        }

        public DataOutputStream getDataOutputStream() {
            return this.dataOutputStream;
        }
    }
}

