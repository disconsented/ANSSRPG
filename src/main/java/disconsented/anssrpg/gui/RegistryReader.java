/*The MIT License (MIT)

Copyright (c) 2015 Disconsented, James Kerr

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
*/
package disconsented.anssrpg.gui;

import disconsented.anssrpg.common.Logging;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityList;
import net.minecraft.item.Item;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by j on 3/09/2015.
 */
public class RegistryReader {
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JList listItem;
    private JList listEntity;
    private JList listBlock;
    private JTextField textFieldSearch;
    private JSplitPane JSplit;
    private final JFrame frame;
    private final ArrayList<String> rawItems;
    private final ArrayList<String> rawBlocks;
    private final ArrayList<String> rawEntities;
    private static RegistryReader instance;

    private RegistryReader() {
        this.frame = new JFrame("Minecraft Registry Reader");
        this.frame.setContentPane(this.panel1);
        this.frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.frame.pack();
        this.frame.setVisible(true);

        this.textFieldSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                RegistryReader.this.updateDisplayedLists();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                RegistryReader.this.updateDisplayedLists();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

        this.rawItems = new ArrayList<String>(Item.itemRegistry.getKeys());
        this.listItem.setListData(this.rawItems.toArray());

        this.rawEntities = new ArrayList(EntityList.idToClassMapping.keySet());
        this.listEntity.setListData(this.rawEntities.toArray());

        this.rawBlocks = new ArrayList<String>(Block.blockRegistry.getKeys());
        this.listBlock.setListData(this.rawBlocks.toArray());


    }

    public static RegistryReader getInstance() {
        if (RegistryReader.instance == null) {
            RegistryReader.instance = new RegistryReader();
        }
        return RegistryReader.instance;
    }

    public void toggleShow() {
        this.frame.setVisible(!this.frame.isShowing());
    }

    public void show() {
        this.frame.setVisible(true);
    }

    private void updateDisplayedLists() {
        String query = this.textFieldSearch.getText();
        Pattern p = Pattern.compile(query);
        if (p == null)
            return;

        ArrayList<String> filteredItems = new ArrayList<>();
        this.filter(this.rawItems, p, filteredItems);
        this.listItem.setListData(filteredItems.toArray());

        ArrayList<String> filteredEntities = new ArrayList<>();
        this.filter(this.rawEntities, p, filteredEntities);
        this.listEntity.setListData(filteredEntities.toArray());

        ArrayList<String> filteredBlocks = new ArrayList<>();
        this.filter(this.rawBlocks, p, filteredBlocks);
        this.listBlock.setListData(filteredBlocks.toArray());
    }

    /**
     * Generic method for filtering items from one list into another
     *
     * @param source
     * @param pattern
     * @param destination
     */
    private void filter(ArrayList<String> source, Pattern pattern, ArrayList<String> destination) {
        for (String item : source) {
            if (pattern.matcher(item).find()) {
                destination.add(item);
            }
        }
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1, true, true));
        panel1.setMinimumSize(new Dimension(600, 800));
        panel1.setPreferredSize(new Dimension(600, 800));
        JSplit = new JSplitPane();
        JSplit.setContinuousLayout(false);
        JSplit.setDividerLocation(700);
        JSplit.setDividerSize(0);
        JSplit.setOrientation(0);
        panel1.add(JSplit, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(0, 0), null, 0, false));
        tabbedPane1 = new JTabbedPane();
        JSplit.setLeftComponent(tabbedPane1);
        final JScrollPane scrollPane1 = new JScrollPane();
        tabbedPane1.addTab("Block", scrollPane1);
        listBlock = new JList();
        scrollPane1.setViewportView(listBlock);
        final JScrollPane scrollPane2 = new JScrollPane();
        tabbedPane1.addTab("Entity", scrollPane2);
        listEntity = new JList();
        scrollPane2.setViewportView(listEntity);
        final JScrollPane scrollPane3 = new JScrollPane();
        tabbedPane1.addTab("Item", scrollPane3);
        listItem = new JList();
        scrollPane3.setViewportView(listItem);
        textFieldSearch = new JTextField();
        JSplit.setRightComponent(textFieldSearch);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}
