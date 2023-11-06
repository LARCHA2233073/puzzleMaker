package com.example.puzzlemaker;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

public class Puzzle extends ImageView {

    public Puzzle(Image image) {
        ImageView imageView = new ImageView(image);

        //source
        imageView.setOnDragDetected( (event) -> {
            Dragboard dragboard = imageView.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent contenu = new ClipboardContent();
            contenu.putImage(imageView.getImage());
            dragboard.setContent(contenu);
        });
        //target
        imageView.setOnDragOver( (event) -> {
            event.acceptTransferModes(TransferMode.MOVE);
        });
        imageView.setOnDragDropped( (event) -> {
            Bounds bounds = new BoundingBox(event.getX(), event.getY(), image.getHeight(), image.getWidth());


        });
    }
}


