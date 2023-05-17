package view;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import db.QueryExecution;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.HashSet;
import java.util.Set;

public class GUI {
    
    private static final String TITLE = "D&D database";
    private static final int PROPORTION = 1;
    private final JFrame frame = new JFrame(TITLE);

    public GUI(final QueryExecution execution) {
        final JPanel canvas = new JPanel(new BorderLayout());
        final JPanel left = new JPanel();
        left.setLayout(new BoxLayout(left, BoxLayout.PAGE_AXIS));
        createButtonsOP().forEach(b -> {
            left.add(Box.createRigidArea(new Dimension(0, 5)));
            left.add(b);
            left.add(Box.createVerticalGlue());
        });
        final JPanel right = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.PAGE_AXIS));
        createButtonsSELECT().forEach(b -> {
            right.add(Box.createRigidArea(new Dimension(0, 5)));
            right.add(b);
            right.add(Box.createVerticalGlue());
        });
        canvas.add(left, BorderLayout.WEST);
        canvas.add(right, BorderLayout.EAST);
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JTextArea result = createTextArea();
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

    private Set<JButton> createButtonsSELECT() {
        final Set<JButton> set = new HashSet<>();
        final JButton campaigns = create(set, "Campagne");
        final JButton sessions = create(set, "Sessioni");
        final JButton parties = create(set, "Parties");
        final JButton protagonists = create(set, "Protagonisti");
        final JButton npcs = create(set, "NPCs");
        final JButton monsters = create(set, "Mostri");
        final JButton races = create(set, "Razze");
        final JButton classes = create(set, "Classi");
        final JButton subclasses = create(set, "Sottoclassi");
        final JButton items = create(set, "Oggetti");
        return set;
    }

    private Set<JButton> createButtonsOP() {
        final Set<JButton> set = new HashSet<>();
        final JButton op1 = create(set, "Aggiungi campagna");
        final JButton op2 = create(set, "Aggiungi sessione");
        final JButton op3 = create(set, "Aggiungi party");
        final JButton op4 = create(set, "Aggiungi turno protagonista");
        final JButton op4b = create(set, "Aggiungi turno mostro");
        final JButton op5 = create(set, "Media danni per protagonista");
        final JButton op6 = create(set, "Aggiungi interazione NPC conosciuto");
        final JButton op7 = create(set, "Aggiungi interazione NPC sconosciuto");
        final JButton op8 = create(set, "Elenca NPC conosciuti in campagna");
        final JButton op9 = create(set, "Mostra composizione del party");
        final JButton op10 = create(set, "Aggiungi oggetto a protagonista");
        final JButton op11 = create(set, "Mostra oggetti di protagonista");
        final JButton op12 = create(set, "Verifica se oggetto in possesso del party");
        return set;
    }

    private JButton create(final Set<JButton> set, final String s) {
        final JButton b = new JButton(s);
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        set.add(b);
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
