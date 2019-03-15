package com.quarix.lab2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Form1 {
    private JPanel mainPanel;
    private JButton openFileBtn;
    private JSpinner spinner1;
    private JRadioButton кобмбинированныйRadioButton;
    private JRadioButton перестановкиRadioButton;
    private JRadioButton изгородиRadioButton;
    private JButton decryptBtn;
    private JButton saveResultBtn;
    private JButton encryptBtn;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JLabel keyLabel;
    private JButton rndBtn;

    public Form1() {

        кобмбинированныйRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keyLabel.setEnabled(true);
                keyLabel.setVisible(true);
                spinner1.setEnabled(true);
                spinner1.setVisible(true);
                rndBtn.setVisible(true);
                rndBtn.setEnabled(true);
            }
        });
        изгородиRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keyLabel.setEnabled(false);
                keyLabel.setVisible(false);
                spinner1.setEnabled(false);
                spinner1.setVisible(false);
                rndBtn.setVisible(false);
                rndBtn.setEnabled(false);
            }
        });
        перестановкиRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keyLabel.setEnabled(true);
                keyLabel.setVisible(true);
                spinner1.setEnabled(true);
                spinner1.setVisible(true);
                rndBtn.setVisible(true);
                rndBtn.setEnabled(true);
            }
        });

        encryptBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textArea1.getText().equals("")) {
                    if (изгородиRadioButton.isSelected())
                        textArea2.setText(HedgeCipher.encrypt(textArea1.getText()));

                    String len = spinner1.getValue().toString();
                    if (len.length() > 1 && checkKey(len)) {
                        if (перестановкиRadioButton.isSelected()) {
                            String text = textArea1.getText();
                            if (text.length() % len.length() == 0) {
                                textArea2.setText(CipherWithKey.encrypt(text, len));
                            } else infoBox("Выбранный ключ не подходит для строки данной длины", "Предупреждение");
                        }

                        if (кобмбинированныйRadioButton.isSelected()) {
                            String text = textArea1.getText();
                            if (text.length() % len.length() == 0) {
                                textArea2.setText(CombiCipher.encrypt(text, len));
                            } else {
                                infoBox("Выбранный ключ не подходит для строки данной длины", "Предупреждение");
                            }
                        }
                    }
                } else infoBox("Указан неверный ключ", "Предупреждение");
            }
        });

        decryptBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textArea2.getText().equals("")) {
                    if (изгородиRadioButton.isSelected())
                        textArea1.setText(HedgeCipher.decrypt(textArea2.getText()));

                    String len = spinner1.getValue().toString();
                    if (len.length() > 1 && checkKey(len)) {
                        if (перестановкиRadioButton.isSelected()) {
                            String text = textArea2.getText();
                            if (text.length() % len.length() == 0) {
                                textArea1.setText(CipherWithKey.decrypt(text, len));
                            } else infoBox("Выбранный ключ не подходит для строки данной длины", "Предупреждение");
                        }

                        if (кобмбинированныйRadioButton.isSelected()) {
                            String text = textArea2.getText();
                            if (text.length() % len.length() == 0) {
                                textArea1.setText(CombiCipher.decrypt(text, len));
                            } else {
                                infoBox("Выбранный ключ не подходит для строки данной длины", "Предупреждение");
                            }
                        }
                    } else infoBox("Не указан текст для дешифровки", "Предупреждение");
                } else infoBox("Указан неверный ключ", "Предупреждение");
            }
        });
        saveResultBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sb = textArea2.getText();
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int retrival = chooser.showSaveDialog(null);
                if (retrival == JFileChooser.APPROVE_OPTION) {
                    try (FileWriter fw = new FileWriter(chooser.getSelectedFile())) {
                        fw.write(sb);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        openFileBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int retrival = chooser.showOpenDialog(null);
                if (retrival == JFileChooser.APPROVE_OPTION) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(chooser.getSelectedFile()))) {
                        String sb = reader.readLine();
                        textArea1.setText(sb);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        rndBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int count = (int) (Math.random() * 9);
                while (count < 2)
                    count = (int) (Math.random() * 9);
                char[] key = new char[count];
                List<Integer> arr = new ArrayList<>();
                for (int i = 1; i <= count; i++)
                    arr.add(i);
                int pos = 0;
                Integer rnd;
                double rnd2;
                while (arr.size() > 1) {
                    rnd2 = Math.random();
                    rnd = (int) (rnd2 * (double) count);
                    if (arr.contains(rnd)) {
                        arr.remove(rnd);
                        key[pos++] = rnd.toString().charAt(0);
                    }
                }
                key[pos] = arr.get(0).toString().charAt(0);
                spinner1.setValue(Integer.parseInt(String.valueOf(key)));
            }
        });
    }

    public static void startup() {
        JFrame frame = new JFrame("Ciphers");
        frame.setContentPane(new Form1().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        frame.setVisible(true);

    }

    public static boolean checkKey(String key) {
        List<String> arr = new ArrayList<>();
        for (int i = 1; i <= key.length(); i++)
            arr.add(String.valueOf(i));
        for (int i = 0; i < key.length(); i++) {
            String current = String.valueOf(key.charAt(i));
            if (arr.contains(current))
                arr.remove(current);
            else return false;
        }
        return true;
    }

    public static void infoBox(String infoMessage, String titleBar) {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}
