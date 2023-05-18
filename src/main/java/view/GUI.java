package view;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import db.Controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class GUI {
    
    private static final String TITLE = "D&D database";
    private static final int PROPORTION = 1;
    private static final int FIELD_LENGTH = 50;
    private final JFrame frame = new JFrame(TITLE);
    private final JTextArea result;
    private final Controller controller;

    public GUI(final Controller controller) {
        this.controller = Objects.requireNonNull(controller);
        final JPanel canvas = new JPanel(new BorderLayout());
        final JPanel left = new JPanel();
        left.setLayout(new BoxLayout(left, BoxLayout.PAGE_AXIS));
        createButtonsOP().stream().forEachOrdered(b -> {
            left.add(Box.createRigidArea(new Dimension(0, 5)));
            left.add(b);
            left.add(Box.createVerticalGlue());
        });
        final JPanel right = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.PAGE_AXIS));
        createButtonsSELECT().stream().forEachOrdered(b -> {
            right.add(Box.createRigidArea(new Dimension(0, 5)));
            right.add(b);
            right.add(Box.createVerticalGlue());
        });
        canvas.add(left, BorderLayout.WEST);
        canvas.add(right, BorderLayout.EAST);
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.result = createTextArea();
        canvas.add(result, BorderLayout.CENTER);
    }

    private JTextArea createTextArea() {
        final Border border = BorderFactory.createLineBorder(Color.RED, 2);
        final JTextArea result = new JTextArea("Words");
        result.setBorder(border);
        result.setBackground(Color.white);
        result.setOpaque(true);
        result.setEditable(false);
        return result;
    }

    private List<JButton> createButtonsSELECT() {
        final List<JButton> l = new ArrayList<>();
        final JButton campaigns = create(l, "Campagne");
        campaigns.addActionListener(e -> this.result.setText(this.controller.selectAll(campaigns.getText())));
        final JButton sessions = create(l, "Sessioni");
        final JButton parties = create(l, "Parties");
        final JButton protagonists = create(l, "Protagonisti");
        final JButton npcs = create(l, "NPCs");
        final JButton monsters = create(l, "Mostri");
        final JButton races = create(l, "Razze");
        final JButton classes = create(l, "Classi");
        final JButton subclasses = create(l, "Sottoclassi");
        final JButton items = create(l, "Oggetti");
        return l;
    }

    private List<JButton> createButtonsOP() {
        final List<JButton> l = new ArrayList<>();
        final JButton op1 = create(l, "Aggiungi campagna");
        op1.addActionListener(e -> {
            final List<String> data = 
                new DataPane("Inserisci numGiocatori e nomeCampagna", List.of(new JLabel("numGiocatori"), new JLabel("nomeCampagna"))).show();
            if (data.size() > 0) {
                this.controller.op1(Integer.parseInt(data.get(0)), data.get(1));
            }
        });
        final JButton op2 = create(l, "Aggiungi sessione");
        final JButton op3 = create(l, "Aggiungi party");
        final JButton op4 = create(l, "Aggiungi turno protagonista");
        final JButton op4b = create(l, "Aggiungi turno mostro");
        final JButton op5 = create(l, "Media danni per protagonista");
        final JButton op6 = create(l, "Aggiungi interazione NPC conosciuto");
        final JButton op7 = create(l, "Aggiungi interazione NPC sconosciuto");
        final JButton op8 = create(l, "Elenca NPC conosciuti in campagna");
        final JButton op9 = create(l, "Mostra composizione del party");
        final JButton op10 = create(l, "Aggiungi oggetto a protagonista");
        final JButton op11 = create(l, "Mostra oggetti di protagonista");
        final JButton op12 = create(l, "Verifica se oggetto in possesso del party");
        return l;
    }

    private JButton create(final List<JButton> l, final String s) {
        final JButton b = new JButton(s);
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        l.add(b);
        return b;
    }

    public void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setVisible(true);
    }
}
