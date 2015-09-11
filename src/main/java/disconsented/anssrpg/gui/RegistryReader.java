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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by j on 3/09/2015.
 */
public class RegistryReader{
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JList listItem;
    private JList listEntity;
    private JList listBlock;
    private JTextField textFieldSearch;
    private JSplitPane JSplit;
    private JFrame frame;
    private ArrayList<String> rawItems;
    private ArrayList<String> rawBlocks;
    private ArrayList<String> rawEntities;
    private static RegistryReader instance = null;
    private RegistryReader(){
        frame = new JFrame("Minecraft Registry Reader");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        textFieldSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateDisplayedLists();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateDisplayedLists();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

        rawItems = new ArrayList<String>(Item.itemRegistry.getKeys());
        listItem.setListData(rawItems.toArray());

        rawEntities = new ArrayList(Arrays.asList(EntityList.func_151515_b().toArray()));
        listEntity.setListData(rawEntities.toArray());

        rawBlocks = new ArrayList<String>(Block.blockRegistry.getKeys());
        listBlock.setListData(rawBlocks.toArray());


    }
    public static RegistryReader getInstance(){
        if (instance == null){
            instance = new RegistryReader();
        }
        return instance;
    }

    public void toggleShow(){
        frame.setVisible(!frame.isShowing());
    }

    public void show(){
        frame.setVisible(true);
    }

    private void updateDisplayedLists(){
        String query = textFieldSearch.getText();
        Pattern p = Pattern.compile(query);
        if(p == null)
            return;

        ArrayList<String> filteredItems = new ArrayList<>();
        filter(rawItems, p, filteredItems);
        listItem.setListData(filteredItems.toArray());

        ArrayList<String> filteredEntities = new ArrayList<>();
        filter(rawEntities, p, filteredEntities);
        listEntity.setListData(filteredEntities.toArray());

        ArrayList<String> filteredBlocks = new ArrayList<>();
        filter(rawBlocks, p, filteredBlocks);
        listBlock.setListData(filteredBlocks.toArray());
    }

    /**
     * Generic method for filtering items from one list into another
     * @param source
     * @param pattern
     * @param destination
     */
    private void filter(ArrayList<String> source, Pattern pattern, ArrayList<String> destination){
        for(String item: source){
            if(pattern.matcher(item).find()){
                destination.add(item);
            }
        }
    }
}
