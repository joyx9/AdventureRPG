package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextBox extends JPanel implements ActionListener {
    protected JTextArea textArea;
    protected JTextField userInput;

    public TextBox(){
        super(new GridBagLayout());
        Font font = new Font("Sans Serif", Font.PLAIN, 18);
        javax.swing.border.Border border = BorderFactory.createLineBorder(Color.WHITE);

        textArea = new JTextArea(7, 20);
        textArea.setFont(font);
        textArea.setEditable(false);
        textArea.setBackground(Color.DARK_GRAY);
        textArea.setForeground(Color.LIGHT_GRAY);
        textArea.setBorder(border);

        userInput = new JTextField(20);
        userInput.setFont(font);
        userInput.setBackground(Color.DARK_GRAY);
        userInput.setForeground(Color.LIGHT_GRAY);
        userInput.addActionListener(this);



        //Add Components to this panel.
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(textArea, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        add(userInput, c);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public JTextArea getTextArea(){
        return textArea;
    }

    public JTextField getUserInput() {
        return userInput;
    }
}
