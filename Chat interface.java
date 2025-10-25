import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChatInterface extends JFrame implements ActionListener {
    private Chatbot chatbot;
    private JTextArea chatArea;
    private JTextField inputField;
    
    public ChatInterface(Chatbot chatbot) {
        this.chatbot = chatbot;
        setTitle("Java AI Chatbot");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        add(new JScrollPane(chatArea), BorderLayout.CENTER);

        inputField = new JTextField();
        inputField.addActionListener(this);
        add(inputField, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userInput = inputField.getText();
        chatArea.append("You: " + userInput + "
");
        String response = chatbot.getResponse(userInput);
        chatArea.append("Bot: " + response + "
");
        inputField.setText("");
    }

    public static void main(String[] args) throws Exception {
        Chatbot chatbot = new Chatbot("intents.json");
        new ChatInterface(chatbot);
    }
      }
