import frontend.views.RoleSelectorWindow;

public class Host extends Thread {

    @Override
    public void run() {
        new RoleSelectorWindow();
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Host());
        Thread thread2 = new Thread(new Guest());

        thread1.start();
        //thread2.start();
    }
}

class Guest extends Thread {

    @Override
    public void run() {
        new RoleSelectorWindow();
    }
}
