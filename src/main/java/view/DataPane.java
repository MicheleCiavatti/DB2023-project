package view;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DataPane {
    
    final JPanel canvas;
    final String title;
    final List<JTextField> fields;

    public DataPane(final String title, final List<JLabel> labels) {
        this.canvas = new JPanel();
        this.title = title;
        canvas.setLayout(new BoxLayout(canvas, BoxLayout.PAGE_AXIS));
        this.fields = new ArrayList<>();
        labels.forEach(l -> {
            fields.add(new JTextField());
            l.setText(l.getText() + ": ");
        });
        Stream.iterate(0, i -> i < labels.size(), i -> i + 1).forEach(i -> {
            final JPanel p = new JPanel();
            p.add(labels.get(i));
            p.add(fields.get(i));
            canvas.add(Box.createRigidArea(null));
            canvas.add(p);
            canvas.add(Box.createVerticalGlue());
        });
    }

    public List<String> show() {
        final int result = JOptionPane.showConfirmDialog(null, canvas, title, JOptionPane.OK_CANCEL_OPTION);
        List<String> data = new ArrayList<>();
        if (result == JOptionPane.OK_OPTION) {
            fields.stream().forEachOrdered(field -> data.add(field.getText()));
        }
        return data;
    }
}
