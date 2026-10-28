
package petselector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class PetSelector extends JFrame {

    String[] pets = {"Bird", "Cat", "Dog", "Rabbit", "Pig"};

    String[] emojis = {"🐦", "🐱", "🐶", "🐰", "🐷"};

    String[] images = {
        "bird.png",
        "cat.png",
        "dog.png",
        "rabbit.png",
        "pig.png"
    };

    JLabel pictureLabel;
    ButtonGroup group;

    public PetSelector() {

        setTitle("RadioButtonDemo");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Panel for the radio buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1));

        group = new ButtonGroup();

        // Panel for displaying the selected animal
        pictureLabel = new JLabel(
            emojis[0],
            SwingConstants.CENTER
        );

        pictureLabel.setFont(new Font("Serif", Font.PLAIN, 100));

        // Create five radio buttons using a loop
        for (int i = 0; i < pets.length; i++) {

            JRadioButton button =
                new JRadioButton(pets[i]);

            group.add(button);
            buttonPanel.add(button);

            final int index = i;

            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    showPet(index);

                    JOptionPane.showMessageDialog(
                        PetSelector.this,
                        "You selected: " + pets[index],
                        "Pet Selected",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                }
            });
        }

        // Select the first animal by default
        buttonPanel.getComponent(0);
        ((JRadioButton) buttonPanel.getComponent(0))
            .setSelected(true);

        add(buttonPanel, BorderLayout.WEST);
        add(pictureLabel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Display the selected animal's picture
    public void showPet(int index) {

        File file = new File("images/" + images[index]);

        if (file.exists()) {

            ImageIcon icon = new ImageIcon(file.getPath());

            Image scaledImage = icon.getImage()
                .getScaledInstance(250, 200, Image.SCALE_SMOOTH);

            pictureLabel.setText("");
            pictureLabel.setIcon(new ImageIcon(scaledImage));

        } else {

            pictureLabel.setIcon(null);
            pictureLabel.setText(emojis[index]);
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PetSelector();
            }
        });
    }
}
