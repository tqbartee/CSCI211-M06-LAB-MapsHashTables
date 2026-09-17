import javax.swing.*;
import java.awt.*;

public class HashTableVisualizer extends JFrame {
    private SimpleHashTable<?, ?> hashTable;

    public HashTableVisualizer(SimpleHashTable<?, ?> hashTable) {
        this.hashTable = hashTable;
        setTitle("SimpleHashTable Visualizer");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JScrollPane scrollPane = new JScrollPane(new HashTablePanel());
        add(scrollPane);
    }

    private class HashTablePanel extends JPanel {
        private final int NODE_WIDTH = 120;
        private final int NODE_HEIGHT = 40;
        private final int HORIZONTAL_GAP = 50;
        private final int VERTICAL_GAP = 20;
        private final int LEFT_MARGIN = 50;
        private final int TOP_MARGIN = 50;

        public HashTablePanel() {
            int capacity = hashTable.getCapacity();
            SimpleHashTable.HashEntry<?, ?>[] buckets = hashTable.getBuckets();
            
            int maxListLength = 0;
            for (SimpleHashTable.HashEntry<?, ?> bucket : buckets) {
                int length = 0;
                SimpleHashTable.HashEntry<?, ?> current = bucket;
                while (current != null) {
                    length++;
                    current = current.next;
                }
                maxListLength = Math.max(maxListLength, length);
            }

            int height = TOP_MARGIN + (capacity * (NODE_HEIGHT + VERTICAL_GAP)) + 100;
            int width = LEFT_MARGIN + Math.max(1, maxListLength) * (NODE_WIDTH + HORIZONTAL_GAP) + 100;
            
            setPreferredSize(new Dimension(width, height));
            setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            SimpleHashTable.HashEntry<?, ?>[] buckets = hashTable.getBuckets();
            int capacity = hashTable.getCapacity();

            for (int i = 0; i < capacity; i++) {
                int x = LEFT_MARGIN;
                int y = TOP_MARGIN + i * (NODE_HEIGHT + VERTICAL_GAP);

                // Draw index label
                g2.setColor(Color.BLACK);
                g2.drawString("Index " + i, x - 45, y + NODE_HEIGHT / 2 + 5);

                // Draw the array slot (bucket head)
                g2.setColor(new Color(220, 220, 220));
                g2.fillRect(x, y, NODE_WIDTH, NODE_HEIGHT);
                g2.setColor(Color.BLACK);
                g2.drawRect(x, y, NODE_WIDTH, NODE_HEIGHT);

                SimpleHashTable.HashEntry<?, ?> current = buckets[i];
                if (current == null) {
                    g2.drawString("null", x + 10, y + NODE_HEIGHT / 2 + 5);
                } else {
                    int nodeCount = 0;
                    while (current != null) {
                        int nodeX = x + nodeCount * (NODE_WIDTH + HORIZONTAL_GAP);
                        
                        // Draw arrow if not the first node
                        if (nodeCount > 0) {
                            int prevNodeX = nodeX - HORIZONTAL_GAP;
                            drawArrow(g2, prevNodeX + NODE_WIDTH, y + NODE_HEIGHT / 2, nodeX, y + NODE_HEIGHT / 2);
                        }

                        // Draw node
                        g2.setColor(new Color(173, 216, 230));
                        g2.fillRect(nodeX, y, NODE_WIDTH, NODE_HEIGHT);
                        g2.setColor(Color.BLACK);
                        g2.drawRect(nodeX, y, NODE_WIDTH, NODE_HEIGHT);

                        String text = current.key + " : " + current.value;
                        FontMetrics fm = g2.getFontMetrics();
                        int textX = nodeX + (NODE_WIDTH - fm.stringWidth(text)) / 2;
                        int textY = y + (NODE_HEIGHT - fm.getHeight()) / 2 + fm.getAscent();
                        g2.drawString(text, textX, textY);

                        current = current.next;
                        nodeCount++;
                    }
                }
            }
        }

        private void drawArrow(Graphics2D g2, int x1, int y1, int x2, int y2) {
            g2.drawLine(x1, y1, x2, y2);
            int arrowSize = 6;
            g2.fillPolygon(new int[]{x2, x2 - arrowSize, x2 - arrowSize},
                           new int[]{y2, y2 - arrowSize, y2 + arrowSize}, 3);
        }
    }
    
    public static void display(SimpleHashTable<?, ?> hashTable) {
        SwingUtilities.invokeLater(() -> {
            HashTableVisualizer visualizer = new HashTableVisualizer(hashTable);
            visualizer.setVisible(true);
        });
    }
}
