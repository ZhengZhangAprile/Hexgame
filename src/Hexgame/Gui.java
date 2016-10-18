package Hexgame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;


/**
 * Created by aprile on 2/09/15.
 */
public class Gui extends Application{
    Hexgon highlighted = null;
    ArrayList<Hexgon> hexgons = new ArrayList<>();
    ArrayList<Hexgon> highlighteds =new ArrayList<>();
    ArrayList<Hexgon> highlightedFinalPoint = new ArrayList<>();
    Text steps=new Text();
    int finalPoint;
    int stepCount=0;
    int seconds = 30;

    /**
     * Highlight the hexgon that the next step can reach.
     * @param game
     * @param x
     * @param startPoint
     */
    public void highlightNextHexgon(String game,int x,int startPoint){
        if(highlighteds.size()!=0){
            for(int i=0;i<highlighteds.size();i++){
                if(i!=finalPoint)
                    highlighteds.get(i).setFill(Color.LIGHTGRAY);
            }
            highlighteds.clear();
        }
        Position position=new Position();
        for(int i=0;i<217;i++){
            if(nextMove.step(game,x,i)){
                highlighted = hexgons.get(i);
                if(i!=startPoint&&i!=finalPoint){
                    highlighted.setFill(Color.GREEN);
                    highlighteds.add(hexgons.get(i));
                }
                if(i==finalPoint){
                    highlighted.setFill(Color.RED);
                    highlightedFinalPoint.add(hexgons.get(i));
                }
            }
        }
    }

    /**
     * Find out the nearest hexgon.
     * @param x
     * @param y
     * @return
     */
    public Hexgon findNearestHexgon(double x,double y){
        double minimalDistance=hexgons.get(0).distance(x,y);
        Hexgon h1=hexgons.get(0);
        for(int i=1;i<hexgons.size();i++){
            if(minimalDistance>hexgons.get(i).distance(x,y)){
                minimalDistance=hexgons.get(i).distance(x,y);
                h1=hexgons.get(i);
            }
        }
        return h1;
    }

    /**
     * Start method to start the javafx
     * @param primaryStage
     * @throws Exception
     */
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 700,700);
        scene.setFill(Color.LIGHTBLUE);
        primaryStage.setScene(scene);

        //datas needed
        ArrayList<Text> numbers = new ArrayList<>();
        Position position = new Position();
        HexGame game = new HexGame();
        String piece = game.getPieces();
        String nook =game.getNooks();
        String cranny = game.getCrannies();
//        ArrayList<Piece> pieces=new ArrayList<>();
        finalPoint=setFinalPoint(nook, piece);
        int startPoint=Integer.parseInt(piece);
        Button reset = new Button("reset");
        Text result = new Text();//print score

        //add buttons
        Button onePlayer = new Button("one player");
        root.getChildren().addAll(onePlayer);
        Button mutiplePlayer = new Button("mutiple player");
        root.getChildren().addAll(mutiplePlayer);

        //one player mode button
        onePlayer.setFont(Font.font(20));
        onePlayer.setLayoutX(200);
        onePlayer.setLayoutY(300);
        onePlayer.setOnMouseClicked(event1 -> {
            root.getChildren().remove(onePlayer);
            root.getChildren().remove(mutiplePlayer);
            Button onePiece = new Button("one piece");
            root.getChildren().addAll(onePiece);
            Button mutiplePiece = new Button("mutiplePiece");
            root.getChildren().addAll(mutiplePiece);

            //one piece mode button
            onePiece.setFont(Font.font(20));
            onePiece.setLayoutX(200);
            onePiece.setLayoutY(300);
            onePiece.setOnMouseClicked(event -> {
                root.getChildren().remove(onePiece);
                root.getChildren().remove(mutiplePiece);
                // Create JavaFX nooks
                ArrayList<javafx.scene.shape.Polygon> jfxnooks = Nook.createJavaFXNook(nook);
                root.getChildren().addAll(jfxnooks);

                //Create JavaFX crannies
                ArrayList<Line> lines = Cranny.createJavaFXCrannies(cranny);
                root.getChildren().addAll(lines);

                //add black boundary
                ArrayList<Polygon> blackBoundary = Board.createBlackBoundary();
                root.getChildren().addAll(blackBoundary);

                //Hexgon and numbers
                for (int i = 0; i < 217; i++) {
                    Point p = position.p[i];
                    Hexgon hexgon = new Hexgon(20 * p.x + 350, -20 * p.y + 350, 22);
                    hexgons.add(hexgon);
                    Text num = new Text();
                    num.setText(String.valueOf(i));
                    num.setLayoutX(20 * p.x + 340);
                    num.setLayoutY(-20 * p.y + 372);
                    numbers.add(num);
                }
                root.getChildren().addAll(hexgons);
                root.getChildren().addAll(numbers);

                //add reset button
                reset.setLayoutX(160);
                root.getChildren().addAll(reset);

                //add a timer to count the time
                Text second = new Text();// print second left on the board
                second.setLayoutX(50);
                second.setLayoutY(60);
                second.setStroke(Color.BLUE);
                second.setFont(Font.font(30));
                Button start = new Button("Start");//start button is for start the time counting.
                root.getChildren().addAll(start);
                start.setOnMouseClicked(event2 -> {
                    for(int i=0;i<216;i++){
                        if (i == finalPoint)
                            hexgons.get(i).setFill(Color.YELLOW);
                        if (i == Integer.parseInt(piece))
                            hexgons.get(i).setFill(Color.AQUA);
                    }
//                    Text result = new Text();//print score
                    result.setLayoutX(200);
                    result.setLayoutY(330);
                    result.setStroke(Color.RED);
                    result.setFont(Font.font(50));
                    DraggablePiece piece1 = new DraggablePiece(piece, nook, this, game.toString(), startPoint, reset,result);
                    root.getChildren().addAll(piece1);
                    root.getChildren().addAll(steps);

                    Timeline timeline = new Timeline(new KeyFrame(
                            Duration.seconds(30),
                            ae -> {
                                //add minimalPath button
                                Button minimalPathButton = new Button("minimalPath");
                                minimalPathButton.setLayoutX(600);
                                root.getChildren().addAll(minimalPathButton);
                                Polyline minimalPath = new Polyline();
                                minimalPathButton.setOnMouseClicked(event3 -> {
                                    minimalPath.setStroke(Color.BLUE);
                                    minimalPath.setStrokeWidth(5);
                                    int a[] = HexGame.minimalPath(game.toString(), startPoint, finalPoint);
                                    for (int i : a) {
                                        Double x = Double.valueOf(position.p[i].x * 20 + 350);
                                        Double y = Double.valueOf(-position.p[i].y * 20 + 370);
                                        minimalPath.getPoints().addAll(x, y);
                                    }
                                    if (root.getChildren().contains(minimalPath)) {
                                        root.getChildren().remove(minimalPath);
                                    } else root.getChildren().add(minimalPath);
                                });

                                double score1;
                                int a1[]=HexGame.minimalPath(game.toString(), startPoint, finalPoint);
                                if (Math.abs(piece1.px - 20 * position.p[finalPoint].x - 350) < 25 && Math.abs(piece1.py + 20 * position.p[finalPoint].y - 360) < 25) {
                                    score1 = ((double)(a1.length - 1)/stepCount) * 100;
                                    result.setText("score is " + Math.round(score1));
                                } else result.setText("Failed,score is null");
                                if(!root.getChildren().contains(result))
                                    root.getChildren().addAll(result);

                                //add next round button
                                Button nextRound = new Button("next round");
                                root.getChildren().add(nextRound);
                                seconds=30;
                                nextRound.setLayoutX(210);
                                nextRound.setOnMouseClicked(event3 -> {
                                    root.getChildren().remove(piece1);
                                    root.getChildren().removeAll(result, second, steps, minimalPath, minimalPathButton, piece1);
                                    for(int i=0;i<highlighteds.size();i++){
                                        if(i!=finalPoint)
                                            highlighteds.get(i).setFill(Color.LIGHTGRAY);
                                    }
                                    highlighteds.clear();
                                    stepCount=0;
                                    steps.setText("step:"+stepCount);
                                    for(int i=0;i<216;i++){
                                        if (i == finalPoint||i == Integer.parseInt(piece))
                                            hexgons.get(i).setFill(Color.LIGHTGRAY);
                                    }

                                    //if piece1 reached the finalPoint, the next round start point will be the final point, we just select a point from the other 17 nooks.
                                    if (Math.abs(piece1.px - 20 * position.p[finalPoint].x - 350) < 25 && Math.abs(piece1.py + 20 * position.p[finalPoint].y - 360) < 25){
                                        String nextRoundPiece = ""+finalPoint;
                                        while(nextRoundPiece.length()<3){
                                            nextRoundPiece += "0";
                                        }
                                        finalPoint=setFinalPoint(nook,nextRoundPiece);
                                    }
                                    root.getChildren().remove(nextRound);
                                });
                            }));
                    timeline.play();

                    class time {
                        java.util.Timer timer = new java.util.Timer();
                        TimerTask task = new TimerTask() {

                            /**
                             * Let timer run
                             */
                            @Override
                            public void run() {
                                seconds--;
                                if (seconds == 0) {
                                    seconds = 0;
                                    task.cancel();
                                }
                                second.setText("Time Left:" + seconds);
                            }
                        };

                        /**
                         * Start the timer
                         */
                        public void start() {
                            timer.scheduleAtFixedRate(task, 1000, 1000);
                        }
                    }
                    time time2 = new time();
                    time2.start();
                    root.getChildren().addAll(second);
                });
            });

            //mutiple piece mode button
            mutiplePiece.setFont(Font.font(20));
            mutiplePiece.setLayoutX(400);
            mutiplePiece.setLayoutY(300);
            mutiplePiece.setOnMouseClicked(event -> {
                root.getChildren().remove(onePiece);
                root.getChildren().remove(mutiplePiece);
            });
        });

        //mutiple player mode button
        mutiplePlayer.setFont(Font.font(20));
        mutiplePlayer.setLayoutX(400);
        mutiplePlayer.setLayoutY(300);
        mutiplePlayer.setOnMouseClicked(event1 -> {
            root.getChildren().remove(mutiplePlayer);
            root.getChildren().remove(onePlayer);

            Button onePiece = new Button("one piece");
            root.getChildren().addAll(onePiece);
            Button mutiplePiece = new Button("mutiplePiece");
            root.getChildren().addAll(mutiplePiece);

            //one piece
            onePiece.setFont(Font.font(20));
            onePiece.setLayoutX(200);
            onePiece.setLayoutY(300);
            onePiece.setOnMouseClicked(event -> {
                root.getChildren().remove(onePiece);
                root.getChildren().remove(mutiplePiece);

                // Create JavaFX nooks
                ArrayList<javafx.scene.shape.Polygon> jfxnooks = Nook.createJavaFXNook(nook);
                root.getChildren().addAll(jfxnooks);

                //Create JavaFX crannies
                ArrayList<Line> lines = Cranny.createJavaFXCrannies(cranny);
                root.getChildren().addAll(lines);

                //add black boundary
                ArrayList<Polygon> blackBoundary = Board.createBlackBoundary();
                root.getChildren().addAll(blackBoundary);

                //Hexgon and numbers
                for (int i = 0; i < 217; i++) {
                    Point p = position.p[i];
                    Hexgon hexgon = new Hexgon(20 * p.x + 350, -20 * p.y + 350, 22);
                    hexgons.add(hexgon);
                    Text num = new Text();
                    num.setText(String.valueOf(i));
                    num.setLayoutX(20 * p.x + 340);
                    num.setLayoutY(-20 * p.y + 372);
                    numbers.add(num);

                }
                root.getChildren().addAll(hexgons);
                root.getChildren().addAll(numbers);

                //add reset button
                reset.setLayoutX(160);
                root.getChildren().addAll(reset);

                //add a timer to count the time
                Text second = new Text();// print second left on the board
                second.setLayoutX(50);
                second.setLayoutY(60);
                second.setStroke(Color.BLUE);
                second.setFont(Font.font(30));

                Button start = new Button("Start");//start button is for start the time counting.
                root.getChildren().addAll(start);
                start.setOnMouseClicked(event2 -> {
                    for (int i = 0; i < 216; i++) {
                        if (i == finalPoint)
                            hexgons.get(i).setFill(Color.YELLOW);
                        if (i == Integer.parseInt(piece))
                            hexgons.get(i).setFill(Color.AQUA);
                    }

                    Piece piece2 = new Piece(piece, nook);
                    root.getChildren().addAll(piece2);

                    Timeline timeline = new Timeline(new KeyFrame(
                            Duration.seconds(30),
                            ae -> {
                                root.getChildren().remove(piece2);
                                root.getChildren().addAll(steps);
                                DraggablePiece piece1 = new DraggablePiece(piece, nook, this, game.toString(), startPoint, reset,result);
                                root.getChildren().addAll(piece1);
                                //add minimalPath button
                                Button minimalPathButton = new Button("minimalPath");
                                minimalPathButton.setLayoutX(600);
                                root.getChildren().addAll(minimalPathButton);
                                Polyline minimalPath = new Polyline();
                                minimalPathButton.setOnMouseClicked(event3 -> {
                                    minimalPath.setStroke(Color.BLUE);
                                    minimalPath.setStrokeWidth(5);
                                    int a[] = HexGame.minimalPath(game.toString(), startPoint, finalPoint);
                                    for (int i : a) {
                                        Double x = Double.valueOf(position.p[i].x * 20 + 350);
                                        Double y = Double.valueOf(-position.p[i].y * 20 + 370);
                                        minimalPath.getPoints().addAll(x, y);
                                    }
                                    if (root.getChildren().contains(minimalPath)) {
                                        root.getChildren().remove(minimalPath);
                                    } else root.getChildren().add(minimalPath);
                                });

                                //add next round button
                                Button nextRound = new Button("next round");
                                root.getChildren().add(nextRound);
                                seconds = 30;
                                nextRound.setLayoutX(210);
                                nextRound.setOnMouseClicked(event3 -> {
                                    root.getChildren().removeAll(result, second, steps, minimalPath, minimalPathButton, piece1);
                                    for (int i = 0; i < highlighteds.size(); i++) {
                                        if (i != finalPoint)
                                            highlighteds.get(i).setFill(Color.LIGHTGRAY);
                                    }
                                    highlighteds.clear();
                                    stepCount = 0;
                                    steps.setText("step:" + stepCount);
                                    for (int i = 0; i < 216; i++) {
                                        if (i == finalPoint || i == Integer.parseInt(piece))
                                            hexgons.get(i).setFill(Color.LIGHTGRAY);
                                    }

                                    //if piece1 reached the finalPoint, the next round start point will be the final point, we just select a point from the other 17 nooks.
                                    if (Math.abs(piece1.px - 20 * position.p[finalPoint].x - 350) < 25 && Math.abs(piece1.py + 20 * position.p[finalPoint].y - 360) < 25) {
                                        String nextRoundPiece = "" + finalPoint;
                                        while (nextRoundPiece.length() < 3) {
                                            nextRoundPiece += "0";
                                        }
                                        finalPoint = setFinalPoint(nook, nextRoundPiece);
                                    }
                                    root.getChildren().remove(nextRound);
                                });
                            }));
                    timeline.play();
                    class time {
                        java.util.Timer timer = new java.util.Timer();
                        TimerTask task = new TimerTask() {
                            @Override
                            public void run() {
                                seconds--;
                                if (seconds == 0) {
                                    seconds = 0;
                                    task.cancel();
                                }
                                second.setText("Time Left:" + seconds);
                            }
                        };

                        public void start() {
                            timer.scheduleAtFixedRate(task, 1000, 1000);
                        }
                    }
                    time time2 = new time();
                    time2.start();
                    root.getChildren().addAll(second);
                });
                result.setLayoutX(200);
                result.setLayoutY(330);
                result.setStroke(Color.RED);
                result.setFont(Font.font(50));
                root.getChildren().addAll(result);
            });

            //mutiple piece mode button
            mutiplePiece.setFont(Font.font(20));
            mutiplePiece.setLayoutX(400);
            mutiplePiece.setLayoutY(300);
            mutiplePiece.setOnMouseClicked(event -> {
                root.getChildren().remove(onePiece);
                root.getChildren().remove(mutiplePiece);
            });
        });
        primaryStage.show();
    }

    public class Hexgon extends Polygon{
        private double x,y;
        double mousex,mousey;

        /**
         * Hexgon constructor for construct hexgons on the board
         * @param x
         * @param y
         * @param side
         */
        public Hexgon(double x,double y,double side){
            this.x=x;
            this.y=y;
            double distance=Math.sqrt((side*side)-(side/2*side/2));
            Double[] d={0.0,0.0,distance,side/2,distance,3*side/2,0.0,2*side,-distance,3*side/2,-distance,side/2};
            this.getPoints().addAll(d);
            this.setLayoutX(x);
            this.setLayoutY(y);
            this.setFill(Color.LIGHTGRAY);
            this.setOnMouseEntered(event -> {
                mousex=event.getSceneX();
                mousey=event.getSceneY();
                Position position=new Position();
                for(int i=0;i<217;i++){
                    if(hexgons.get(i).getFill().equals(Color.LAWNGREEN))
                        hexgons.get(i).setFill(Color.LIGHTGRAY);
                }
                for(int i=0;i<217;i++){
                    Point p=position.p[i];
                    if(Math.abs(mousex-20*p.x-350)<10&&Math.abs(mousey+20*p.y-350)<10){
                        if(!hexgons.get(i).getFill().equals(Color.AQUA)&&!hexgons.get(i).getFill().equals(Color.YELLOW)&&!hexgons.get(i).getFill().equals(Color.RED))
                            hexgons.get(i).setFill(Color.LAWNGREEN);
                    }
                }
            });
        }

        /**
         * Calculate the distance between this piece to a point
         * @param x
         * @param y
         * @return
         */
        private double distance(double x, double y){
            return Math.sqrt((this.x-x)*(this.x-x)+(this.y-y)*(this.y-y));
        }
    }

    public class DraggablePiece extends Piece{
        private double mousex,mousey;
        Gui gui;
        double px,py;
        double x,y;

        /**
         * DraggablePiece constructor for construct pieces that can be dragged
         * @param piece
         * @param nook
         * @param gui
         * @param game
         * @param startPoint
         * @param reset
         * @param result
         */
        public DraggablePiece(String piece,String nook,Gui gui,String game,int startPoint,Button reset,Text result){
            super(piece, nook);
            Position position=new Position();
            Point p=position.p[Integer.parseInt(piece)];
            this.gui=gui;

            reset.setOnMouseClicked(event1 -> {
                this.setLayoutX(position.p[startPoint].x + 1);
                this.setLayoutY(position.p[startPoint].y - 1);
                stepCount=0;
            });
            // when press the mouse , we can get x , y of the piece
            this.setOnMousePressed(event -> {
                mousex = event.getSceneX();
                mousey = event.getSceneY();
                px = mousex;
                py = mousey;
                if (Math.abs(this.px - 20 * position.p[finalPoint].x - 350) < 25 && Math.abs(this.py + 20 * position.p[finalPoint].y - 360) < 25) {
                    result.setText("You win");
                }

                for (int i = 0; i < 217; i++) {
                    Point pFrom = position.p[i];
                    if (Math.abs(px - pFrom.x * 20 - 350) < 10 && Math.abs(py + pFrom.y * 20 - 370) < 10) {
                        highlightNextHexgon(game, i, startPoint);
                        break;
                    }
                }
                x = this.getLayoutX();
                y = this.getLayoutY();
                this.toFront();
            });

            this.setOnMouseDragged(event -> {
                mousex = event.getSceneX();
                mousey = event.getSceneY();
                this.setLayoutX(mousex - 350 - 20 * p.x);
                this.setLayoutY(mousey - 368 + 20 * p.y);
            });

            this.setOnMouseReleased(event -> {
                Hexgon h = gui.findNearestHexgon(mousex, mousey);
                int flag = 0;
                A:
                for (int i = 0; i < 217; i++) {
                    Point pTo = position.p[i];
                    for (int j = 0; j < 217; j++) {
                        Point pFrom = position.p[j];
                        if (Math.abs(px - pFrom.x * 20 - 350) < 10 && Math.abs(py + pFrom.y * 20 - 370) < 10) {
                            if (Math.abs(h.getLayoutX() - pTo.x * 20 - 350) < 10 && Math.abs(h.getLayoutY() + pTo.y * 20 - 360) < 15) {
                                if (nextMove.step(game, j, i)) {
                                    stepCount++;
                                    steps.setText("Step:" + stepCount);
                                    steps.setLayoutX(600);
                                    steps.setLayoutY(100);
                                    steps.setStroke(Color.BLUE);
                                    steps.setFont(Font.font(30));
                                    this.setLayoutX(h.getLayoutX() - 350 - 20 * p.x);
                                    this.setLayoutY(h.getLayoutY() - 350 + 20 * p.y);
                                    px = this.getCenterX();
                                    py = this.getCenterY();
                                    flag = 1;
                                    break A;
                                }
                            }
                        }
                    }
                }
                if (flag == 0) {
                    this.setLayoutX(x);
                    this.setLayoutY(y);
                }
            });
            }
        }
    /**
     * Set the destination
     * @param nook
     * @param piece
     * @return
     */
    public int setFinalPoint(String nook,String piece){
        String []nooks = new String[17];
        String nookOri[]=new String[17];
        for(int i =0;i<17;i++){
            if(!nook.substring(4*i,4*i+3).equals(piece)){
                nookOri[i]=nook.substring(4 * i + 3, 4 * i + 4);
                nooks[i]=nook.substring(4*i,4*i+3);
            }
        }
        Random r=new Random();
        int finalPoint = Integer.parseInt(nooks[r.nextInt(17)]);
        return finalPoint;
    }
}