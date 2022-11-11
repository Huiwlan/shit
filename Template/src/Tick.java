public class Tick {
    static int updates = 4000;

    public static void main(String[] args) {
        int updateUnit = 1000000;
        long executionStamp = System.nanoTime() / updateUnit;


        //GameLoop
        while (true) {
            long now = System.nanoTime() / updateUnit;
            long diff = now - executionStamp;
            long interval = updateUnit / updates;
            if (diff > interval) {
                System.out.println("tick");
                executionStamp = System.nanoTime() / updateUnit;

            }

        }
    }
}
