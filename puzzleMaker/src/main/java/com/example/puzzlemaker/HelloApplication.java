package com.example.puzzlemaker;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws FileNotFoundException {

        //creation des images
        ImageView imageView0 = new ImageView(new Image(new FileInputStream("images//mario0.jpg")));
        ImageView imageView1 = new ImageView(new Image(new FileInputStream("images//mario1.jpg")));
        ImageView imageView2 = new ImageView(new Image(new FileInputStream("images//mario2.jpg")));
        ImageView imageView3 = new ImageView(new Image(new FileInputStream("images//mario3.jpg")));
        ImageView imageView4 = new ImageView(new Image(new FileInputStream("images//mario4.jpg")));
        ImageView imageView5 = new ImageView(new Image(new FileInputStream("images//mario5.jpg")));
        ImageView imageView6 = new ImageView(new Image(new FileInputStream("images//mario6.jpg")));
        ImageView imageView7 = new ImageView(new Image(new FileInputStream("images//mario7.jpg")));
        ImageView imageView8 = new ImageView(new Image(new FileInputStream("images//mario8.jpg")));

        //arranger les images aléatoirement
        GridPane gridpane = new GridPane();
        //
        ArrayList<Image> imagesFinales = new ArrayList<>();
        try {
            Collections.addAll(imagesFinales,
                    new Image(new FileInputStream("images//mario0.jpg")), new Image(new FileInputStream("images//mario1.jpg")),
                    new Image(new FileInputStream("images//mario2.jpg")), new Image(new FileInputStream("images//mario3.jpg")),
                    new Image(new FileInputStream("images//mario4.jpg")), new Image(new FileInputStream("images//mario5.jpg")),
                    new Image(new FileInputStream("images//mario6.jpg")), new Image(new FileInputStream("images//mario7.jpg")),
                    new Image(new FileInputStream("images//mario8.jpg")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        //scrambler
        ArrayList<ImageView> imageViewList = new ArrayList<>();

        Collections.addAll(imageViewList, imageView0, imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8);



        List<Image> imageList = new ArrayList<>();
        try {
            Collections.addAll(imageList,
                    new Image(new FileInputStream("images//mario0.jpg")), new Image(new FileInputStream("images//mario1.jpg")),
                    new Image(new FileInputStream("images//mario2.jpg")), new Image(new FileInputStream("images//mario3.jpg")),
                    new Image(new FileInputStream("images//mario4.jpg")), new Image(new FileInputStream("images//mario5.jpg")),
                    new Image(new FileInputStream("images//mario6.jpg")), new Image(new FileInputStream("images//mario7.jpg")),
                    new Image(new FileInputStream("images//mario8.jpg")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        gridpane.add(imageViewList.get(0), 0, 0);
        gridpane.add(imageViewList.get(1), 1, 0);
        gridpane.add(imageViewList.get(2), 2, 0);
        gridpane.add(imageViewList.get(3), 0, 1);
        gridpane.add(imageViewList.get(4), 1, 1);
        gridpane.add(imageViewList.get(5), 2, 1);
        gridpane.add(imageViewList.get(6), 0, 2);
        gridpane.add(imageViewList.get(7), 1, 2);
        gridpane.add(imageViewList.get(8), 2, 2);

        Scene scene = new Scene(gridpane);

        Collections.shuffle(imageList);
        for (int i = 0; i < 9; i++){
            imageViewList.get(i).setImage(imageList.get(i));
        }

        scene.setOnKeyPressed( event -> {
            if (event.isControlDown() && event.getCode() == KeyCode.M){
                Collections.shuffle(imageList);
                for (int i = 0; i < 9; i++){
                    imageViewList.get(i).setImage(imageList.get(i));
                }
            }
        });

        //drag and drop
        for (ImageView i : imageViewList) {

                i.setOnDragDetected( (event) -> {
                    Dragboard dragboard = i.startDragAndDrop(TransferMode.MOVE);
                    ClipboardContent contenu = new ClipboardContent();
                    contenu.putImage(i.getImage());
                    dragboard.setContent(contenu);
                });
                i.setOnDragOver( (event) -> {
                    event.acceptTransferModes(TransferMode.MOVE);
                });
                i.setOnDragDropped( (event) -> {
                    /*
                    ImageView imageViewOrigine = (ImageView) event.getGestureSource();
                    ImageView imageViewTarget  = (ImageView) event.getGestureTarget();

                    ImageView target = new ImageView(imageViewTarget.getImage());
                    ImageView source = new ImageView(imageViewOrigine.getImage());

                    int indexOrigine = imageViewList.indexOf(imageViewOrigine);
                    int indexTarget = imageViewList.indexOf(imageViewTarget);

                    System.out.println("index origine : " + indexOrigine + " et index target : " + indexTarget);


                    int indexImageOrigine = 0;
                    int indexImageTarget = 0;

                    for (int f = 0; f < imagesFinales.size(); f++) {
                        if (imageViewList.get(indexOrigine).getImage().equals(imagesFinales.get(f)))
                            indexImageOrigine = f;
                    }

                    for (int f = 0; f < imagesFinales.size(); f++) {
                        if (imageViewList.get(indexTarget).getImage().equals(imagesFinales.get(f)))
                            indexImageTarget = f;
                    }

                    imageViewList.get(indexOrigine).setImage(imagesFinales.get(indexTarget));
                    imageViewList.get(indexTarget).setImage(imagesFinales.get(indexOrigine));





                    for (int f = 0; f < imagesFinales.size(); f++) {
                        if (imagesFinales.get(f) == imageViewOrigine.getImage())
                            indexOrigine = f;
                    }
                    for (int f = 0; f < imagesFinales.size(); f++) {
                        if (imagesFinales.get(f) == ((ImageView) event.getGestureTarget()).getImage())
                            indexTarget = f;
                    }



                    System.out.println("index origine : " + indexOrigine + " et index target : " + indexTarget);

                    for (ImageView imageView : imageViewList) {
                        if (imageView == (ImageView) event.getGestureSource())
                            imageView.setImage(imagesFinales.get(indexOrigine));
                    }
                    for (ImageView imageView : imageViewList) {
                        if (imageView == (ImageView) event.getGestureTarget())
                            imageView.setImage(imagesFinales.get(indexTarget));
                    }



                    /*
                    for (ImageView imageView : imageViewList) {
                        if (imageView == imageViewOrigine) {
                            imageView.setImage(((ImageView) event.getGestureTarget()).getImage());
                        }
                    }

                     */
                    ((ImageView) event.getGestureSource()).setImage(((ImageView) event.getGestureTarget()).getImage());
/*
                    for (ImageView imageView : imageViewList) {
                        if (imageView == imageViewTarget) {
                            imageView.setImage(imageViewOrigine.getImage());
                        }
                    }

 */
                    ((ImageView) event.getGestureTarget()).setImage(event.getDragboard().getImage());


                     /*
                    Boolean puzzleComplete = true;
                    System.out.println("debut de verif : " + puzzleComplete);

                    for (int k = 0; k < imageViewList.size(); k++) {
                        if (imageViewList.get(k).getImage() != imagesFinales.get(k)) {
                            puzzleComplete = false;
                            System.out.println(imageViewList.get(k).getImage() + "    et        " + imagesFinales.get(k));
                        }


                        System.out.print(puzzleComplete);
                    }
                    System.out.println(puzzleComplete);

                      */

                    event.setDropCompleted(true);
                    event.consume();

                });
        }

        //verifier si le puzzle est completé


        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}