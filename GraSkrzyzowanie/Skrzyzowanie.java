

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

public class Skrzyzowanie extends JPanel {
    public Skrzyzowanie() {
        super();
        //this.setLayout(null);

        this.ml = new ML();
        this.addMouseListener(ml);
        this.addMouseMotionListener(ml);

        this.kl = new KL();
        this.addKeyListener(kl);

        initSkrzyzowanie();

//        try {
//            actionLights();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        createCarListAndLightList();

//        actionMoveCar(auto1);
//        actionMoveCar(auto2);
//        actionMoveCar(auto3);


    }
//    private Swietla trafficLightRight = new Swietla(450,450,States.Right);
//    private Swietla trafficLightUp = new Swietla(785,450,States.Up);
//    private Swietla trafficLightLeft = new Swietla(785,130,States.Left);
//    private Swietla trafficLightDown = new Swietla(450,130,States.Down);
    private Swietla trafficLightRight ;
    private Swietla trafficLightUp ;
    private Swietla trafficLightLeft ;
    private Swietla trafficLightDown ;
//    private Car auto1 = new Car(540,10,Color.RED, States.Down);
//    private Car auto2 = new Car(0,350,Color.BLUE, States.Right);
//    private Car auto3 = new Car(640,700,Color.RED,States.Up);
    private Car auto1;
    private Car auto2;
    private Car auto3;
    private Car controlledCar;
//    private ArrayList<Car> carList = new ArrayList<>() ;
//    private final ArrayList<Swietla> lightList = new ArrayList<>();
    private ArrayList<Car> carList;
    private ArrayList<Swietla> lightList;
    private Image doublebufferImage;
    private Graphics2D doublebufferGraphic;
    private Graphics2D device;
    private ML ml;
    private KL kl;

    private Toolkit tlotoolkit;
    private Image tloimage;
    private int SPEED = 2;
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        tlotoolkit = Toolkit.getDefaultToolkit();
        tloimage = tlotoolkit.getImage("Background.jpg");
        g2d.drawImage(tloimage,0,0,1280,720,null);
        g2d.setColor(Color.GRAY);
        g2d.fillRect(0,250,1280,200);
        g2d.fillRect(540,0,200,720);
        g2d.setColor(Color.WHITE);
        for(int i = 0;i<=1280;i+=100){
            if(i<540 || i>740) g2d.fillRect(i,345,25,5);
        }
        for(int i = 0;i<=720;i+=100){
            if(i<250 || i>450) g2d.fillRect(635,i,5,25);
        }
        lightList.forEach(trafLight -> trafLight.drawSwietla(g2d));
//        trafficLightRight.drawSwietla(g2d);
//        trafficLightUp.drawSwietla(g2d);
//        trafficLightLeft.drawSwietla(g2d);
//        trafficLightDown.drawSwietla(g2d);
        repaint();
        carList.forEach(car -> car.drawCar(g2d));
//        auto1.drawCar(g2d);
//        auto2.drawCar(g2d);
//        auto3.drawCar(g2d);
        repaint();

    }
    private void actionLights() throws InterruptedException {
        Thread fred = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lightList.forEach(sw -> sw.turnRed());
                    repaint();
                    while (true){
                        for (Swietla curSwietla : lightList){
                            curSwietla.turnYellowAndRed();
                            repaint();
                            Thread.sleep(500);
                            curSwietla.turnGreen();
                            curSwietla.isGreen = true;
                            curSwietla.isRed = false;
                            repaint();
                            Thread.sleep(5000);
                            curSwietla.turnYellow();
                            repaint();
                            Thread.sleep(500);
                            curSwietla.turnRed();
                            curSwietla.isRed = true;
                            curSwietla.isGreen = false;
                            repaint();
                            Thread.sleep(5000);
                        }
                    }
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        fred.start();
    }

//    public void initialize(){
//        int width = 1280;
//        int height = 720;
//        if(doublebufferImage==null){
//            doublebufferImage = createImage(width, height);
//            doublebufferGraphic = (Graphics2D) doublebufferImage.getGraphics();
//            device = (Graphics2D) getGraphics();
//        }
//        doublebufferGraphic.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
//        device.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//    }


    private void actionMoveCar(Car auto){
        auto.isTurnable = true;
        Thread move = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (auto.curState.equals(States.Down) && !auto.stopCar) {
                        auto.setY(auto.getY() +SPEED);
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        if (auto.getY() == 720) {
                            auto.setX(640);
                            auto.setY(790);
                            auto.setCurrentState(States.Up);
                            auto.isTurnable = true;
                            auto.nextState = auto.random();
                            auto.inCenter = false;
                        }
                    }
                    if (auto.curState.equals(States.Up)&& !auto.stopCar) {
                        auto.setY(auto.getY() - SPEED);
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        if (auto.getY() == -220) {
                            auto.setX(540);
                            auto.setY(-200);
                            auto.setCurrentState(States.Down);
                            auto.isTurnable = true;
                            auto.nextState = auto.random();
                            auto.inCenter = false;
                        }
                    }
                    if (auto.curState.equals(States.Right) && !auto.stopCar) {
                        auto.setX(auto.getX() + SPEED);
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        if (auto.getX() == 1280) {
                            auto.setX(1400);
                            auto.setY(250);
                            auto.setCurrentState(States.Left);
                            auto.isTurnable = true;
                            auto.nextState = auto.random();
                            auto.inCenter = false;
                        }
                    }
                    if (auto.curState.equals(States.Left) && !auto.stopCar) {
                        auto.setX(auto.getX() - SPEED);
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        if (auto.getX() == -200) {
                            auto.setX(-150);
                            auto.setY(350);
                            auto.setCurrentState(States.Right);
                            auto.isTurnable = true;
                            auto.nextState = auto.random();
                            auto.inCenter = false;
                        }
                    }
                    carReactsToTrafficLight(auto);
                    auto.updateCarBounds(auto.getX(),auto.getY());
                }
            }
        });
        move.start();
    }
    private void changeColorOnClick() {
        Thread fred2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (ml.mousePressed) {
                        System.out.println("Pressed Mouse");
                        for (Car car : carList) {
                            if (checkIfClickedOnCar(car)) car.setColor(randomColor());
                        }
                    }
                    try {
                        Thread.sleep(80); //TRZEBA DODAĆ TO ŻEBY DZIAŁAŁ MOUSE LISTENER
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        fred2.start();
    }
    private boolean checkIfClickedOnCar(Car car) {
        for (Shape bound : car.carBounds) {;
            if (bound.contains(ml.x,ml.y)) return true;
        }
        return false;
    }

    private Color randomColor() {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return new Color(r, g, b);
    }
    private void checkIfPressedKey() {
        Thread fred3 = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean spawnCar = true;
                while (true) {
                    if (kl.pressedChar == 10 && spawnCar) {
                        spawnCar = false;
                        controlledCar = new Car(30,30,Color.PINK,States.noJoint);
                        controlledCar.setIsControlled(true);
                        carList.add(controlledCar);
                    }
                    if (!spawnCar && kl.pressedChar == 37) {
                        controlledCar.setX(controlledCar.getX() - SPEED);
                    }
                    if (!spawnCar && kl.pressedChar == 38) {
                        controlledCar.setY(controlledCar.getY() - SPEED);
                    }
                    if (!spawnCar && kl.pressedChar == 39) {
                        System.out.println("RIGHT");
                        controlledCar.setX(controlledCar.getX() + SPEED);
                    }
                    if (!spawnCar && kl.pressedChar == 40) {
                        controlledCar.setY(controlledCar.getY() + SPEED);
                    }
                    if (kl.pressedChar == 65) { //A
                        carList.forEach(Car::stopCar);
                    }
                    //S
                    if (kl.pressedChar == 83) {
                        carList.forEach(Car::runCar);
                    }
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        fred3.start();
    }
    private Swietla getTrafficLightByState(States state) {
        if (state.equals(States.Up)) return trafficLightUp;
        if (state.equals(States.Down)) return trafficLightDown;
        if (state.equals(States.Left)) return trafficLightLeft;
        if (state.equals(States.Right)) return trafficLightRight;
        return trafficLightUp;
    }
    private void carReactsToTrafficLight(Car auto) {
        if (auto.currentState().equals(States.Center) && auto.isTurnable) {
            Swietla curTrafficLight = getTrafficLightByState(auto.curState);  //DODANE
            if (curTrafficLight.isGreen || auto.inCenter) {
                auto.runCar();
                if (auto.nextState.equals(States.Right)) {
                    auto.turnRight(auto.curState);
                }
                if (auto.nextState.equals(States.Left)) {
                    auto.turnLeft(auto.curState);
                }
                if (auto.nextState.equals(States.noJoint)) {
                    auto.noJoint(auto.curState);
                }
                auto.inCenter = true;
            }
            else auto.stopCar();
        }
    }
    private void createCarListAndLightList(){
        carList = new ArrayList<>();
        lightList = new ArrayList<>();
        createCarsAndLights();
        lightList.add(trafficLightRight);
        lightList.add(trafficLightUp);
        lightList.add(trafficLightLeft);
        lightList.add(trafficLightDown);
        carList.add(auto1);
        carList.add(auto2);
        carList.add(auto3);
    }
    private void createCarsAndLights() {
        trafficLightRight = new Swietla(450,450,States.Right);
        trafficLightUp = new Swietla(785,450,States.Up);
        trafficLightLeft = new Swietla(785,130,States.Left);
        trafficLightDown = new Swietla(450,130,States.Down);

        auto1 = new Car(540,10,Color.RED, States.Down);
        auto2 = new Car(0,350,Color.BLUE, States.Right);
        auto3 = new Car(640,700,Color.RED,States.Up);

    }
    private void initSkrzyzowanie() {
        createCarListAndLightList();
        for (Car car: carList) {
            if (!car.getIsControlled()) actionMoveCar(car);
        }
        try {
            actionLights();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        changeColorOnClick();
        checkIfPressedKey();
    }
}


