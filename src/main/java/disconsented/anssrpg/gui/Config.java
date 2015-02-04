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

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityList;
import net.minecraft.item.Item;
import disconsented.anssrpg.config.JsonConfigHandler;
import disconsented.anssrpg.data.PerkStore;
import disconsented.anssrpg.data.SkillStore;
import disconsented.anssrpg.perk.BlockPerk;
import disconsented.anssrpg.perk.EntityPerk;
import disconsented.anssrpg.perk.ItemPerk;
import disconsented.anssrpg.perk.Perk;
import disconsented.anssrpg.perk.RegexBlockPerk;
import disconsented.anssrpg.perk.RegexEntityPerk;
import disconsented.anssrpg.perk.RegexItemPerk;
import disconsented.anssrpg.perk.Requirement;
import disconsented.anssrpg.perk.Requirement.Action;
import disconsented.anssrpg.perk.TitlePerk;
import disconsented.anssrpg.skill.objects.BlockSkill;
import disconsented.anssrpg.skill.objects.BlockXP;
import disconsented.anssrpg.skill.objects.EntitySkill;
import disconsented.anssrpg.skill.objects.EntityXP;
import disconsented.anssrpg.skill.objects.ItemSkill;
import disconsented.anssrpg.skill.objects.ItemXP;
import disconsented.anssrpg.skill.objects.Skill;
import disconsented.anssrpg.skill.objects.XPGain;

public class Config {

	private static JFrame frame;
	private static JTextField txtSkillName;
	private static JTextField txtSkillObjectName;
	private final static ButtonGroup buttonGroup = new ButtonGroup();
	private static JTextField textPerkName;
	private static JTextField textPerkObject;
	private static JTextField textPerkReqName;
	private static JTextField textPerkExtraData;
	private static JList listPerk = new JList();
	private static JList listRequirement = new JList();
	private static JList listSkillExp = new JList();
	private static JList listSkill = new JList();
	private static ArrayList<Perk> perks = PerkStore.getPerks();
	private static ArrayList<Skill> skills = SkillStore.getSkills();
	private static Perk currentPerk = null;
	private static Skill currentSkill = null;
	private static Requirement currentReq = null;
	private static XPGain currentExp = null;
	private static JEditorPane editorPerkDescription = new JEditorPane();
	private static JSpinner spinnerPerkAction = new JSpinner();
	private static JSpinner spinnerPerkType = new JSpinner();
	private static JSpinner spinnerPointCost = new JSpinner();
	private static JList listRegBlock = new JList();
	private static JList listRegEntity = new JList();
	private static JList listRegItem = new JList();
	private static JSpinner spinnerSkillType = new JSpinner();
	private static JSpinner spinnerExpXP = new JSpinner();
	private static DefaultListModel entityModel = new DefaultListModel();
	private static DefaultListModel itemModel = new DefaultListModel();
	private static DefaultListModel blockModel = new DefaultListModel();
	private static ArrayList<String> entityList = new ArrayList<String>();
	private static ArrayList<String> itemList = new ArrayList<String>();
	private static ArrayList<String> blockList = new ArrayList<String>();

	/**
	 * Create the application.
	 */
	public Config() {
		initialize();
	}

	
	protected static void deleteCurrentExperience() {
		currentSkill.getExp().remove(currentExp);
		currentExp = null;
		listSkillExp.setSelectedIndex(-1);
		updateSkillExpList();
		
	}
	protected static void deleteCurrentPerk() {
		perks.remove(currentPerk);
		currentPerk = null;
		updatePerkList();		
		updateCurrentPerk();
	}
	protected static void deleteCurrentRequirement() {
		if (listRequirement.getSelectedIndex() > -1){
			currentPerk.requirements.remove(listRequirement.getSelectedIndex());
			listRequirement.setSelectedIndex(-1);
			updateReqList();	
		}
		
		
	}
	protected static void deleteCurrentSkill() {
		skills.remove(currentSkill);
		currentSkill = null;
		listSkill.setSelectedIndex(-1);
		updateSkillList();
		updateCurrentSkill();
	}
	public static DefaultListModel getBlockModel() {
        return blockModel;
    }
	public static DefaultListModel getEntityModel() {
        return entityModel;
    }
	public static DefaultListModel getItemModel() {
        return itemModel;
    }

	public static ArrayList<Perk> getPerks(){
	    return perks;
	}

	public static ArrayList<Skill> getSkills(){
	    return skills;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public static void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 702, 442);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		perks = PerkStore.getPerks();
		skills = SkillStore.getSkills();
		/**
		 * //New//
			Clears all data
			Popup saying data has been cleared
		 */
		
		JMenuItem mntmNew = new JMenuItem("New");
		menuBar.add(mntmNew);
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PerkStore.Clear();
				SkillStore.Clear();
				perks.clear();
                skills.clear();
				updatePerkList();
				updateSkillList();
				JOptionPane.showMessageDialog(null,"All data has been cleared from memory");
			}
		});
		mntmNew.addMouseListener(new MouseAdapter() {			
		});
		JMenuItem mntmSave = new JMenuItem("Save");
		menuBar.add(mntmSave);
		
		JMenuItem mntmLoad = new JMenuItem("Load");
		mntmLoad.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		        disconsented.anssrpg.data.PerkStore.Clear();
                disconsented.anssrpg.data.SkillStore.Clear();
                JsonConfigHandler.loadPerkAndSkill();
		    }
		});
		menuBar.add(mntmLoad);
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Comit everything to memory then save to disk
				JsonConfigHandler.savePerkAndSkill();				
			}
		});
		mntmSave.addMouseListener(new MouseAdapter() {
			
		});
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panelPerk = new JPanel();
		panelPerk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				updatePerkList();
			}
		});
		tabbedPane.addTab("Perk", null, panelPerk, null);
		panelPerk.setLayout(null);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 11, 141, 322);
		panelPerk.add(scrollPane_3);
		
		/**
		 * //Perk Selected//
			setPerk(selected perk)
		 */
		listPerk.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listPerk.setSelectedIndex(0);
		
		listPerk.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				updateCurrentPerk();
			}
		});
		scrollPane_3.setViewportView(listPerk);
		
		JLabel lblPerkName = new JLabel("Perk Name");
		lblPerkName.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane_3.setColumnHeaderView(lblPerkName);
		
		textPerkName = new JTextField();
		textPerkName.setBounds(246, 43, 149, 20);
		panelPerk.add(textPerkName);
		textPerkName.setColumns(10);
		
		textPerkObject = new JTextField();
		textPerkObject.setBounds(246, 170, 149, 20);
		panelPerk.add(textPerkObject);
		textPerkObject.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setBounds(163, 42, 73, 14);
		panelPerk.add(lblName);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescription.setBounds(163, 70, 73, 14);
		panelPerk.add(lblDescription);
		
		
		editorPerkDescription.setBounds(246, 75, 303, 51);
		panelPerk.add(editorPerkDescription);
		
		JLabel lblPointCost = new JLabel("Point cost");
		lblPointCost.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPointCost.setBounds(163, 137, 73, 14);
		panelPerk.add(lblPointCost);
		
		JLabel lblObjectName = new JLabel("Object name");
		lblObjectName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblObjectName.setBounds(163, 168, 73, 14);
		panelPerk.add(lblObjectName);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(246, 202, 124, 130);
		panelPerk.add(scrollPane_4);
		listRequirement.setSelectedIndex(0);
		listRequirement.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		listRequirement.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				updateCurrentRequirment();
			}
		});
		listRequirement.setLocation(0, 200);
		scrollPane_4.setViewportView(listRequirement);
		
		JLabel lblNewLabel_5 = new JLabel("Requirement");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5.setBounds(163, 198, 73, 14);
		panelPerk.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Action");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6.setBounds(405, 202, 63, 14);
		panelPerk.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Name");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_7.setBounds(405, 227, 63, 14);
		panelPerk.add(lblNewLabel_7);
		
		JLabel lblExtraData = new JLabel("Extra Data");
		lblExtraData.setHorizontalAlignment(SwingConstants.RIGHT);
		lblExtraData.setBounds(405, 252, 63, 14);
		panelPerk.add(lblExtraData);
		
		
		spinnerPerkAction.setModel(new SpinnerListModel(new String[] {"HAVE", "DONT", "LEVEL_EQUALS", "LEVEL_GREATER", "LEVEL_LESS"}));
		spinnerPerkAction.setBounds(478, 202, 187, 14);
		panelPerk.add(spinnerPerkAction);
		
		textPerkReqName = new JTextField();
		textPerkReqName.setBounds(478, 224, 187, 20);
		panelPerk.add(textPerkReqName);
		textPerkReqName.setColumns(10);
		
		textPerkExtraData = new JTextField();
		textPerkExtraData.setBounds(478, 249, 187, 20);
		panelPerk.add(textPerkExtraData);
		textPerkExtraData.setColumns(10);
		
		
		spinnerPerkType.setModel(new SpinnerListModel(new String[] {"Block", "Item", "Entity", "RegexBlock", "RegexItem", "RegexEntity", "Title"}));
		spinnerPerkType.setBounds(246, 11, 149, 20);
		panelPerk.add(spinnerPerkType);
		
		JLabel lblType = new JLabel("Type");
		lblType.setHorizontalAlignment(SwingConstants.RIGHT);
		lblType.setBounds(163, 11, 73, 14);
		panelPerk.add(lblType);
		/**
		 * //Perk New//
			Add new blank perk to list
			Update list
			Set to current perk
		 */
		JButton btnPerkNew = new JButton("New");
		btnPerkNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newPerk();
			}
		});
		btnPerkNew.setBounds(10, 338, 60, 23);
		panelPerk.add(btnPerkNew);
		
		/**
		 * //Perk Delete//
			Remove currently selected perk from list
			Set selected perk to null
		 */
		JButton btnPerkDelete = new JButton("Delete");
		btnPerkDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteCurrentPerk();
			}
		});
		btnPerkDelete.setBounds(67, 338, 86, 23);
		panelPerk.add(btnPerkDelete);
		/**
		//Update Perk//
		Set fields
		setPerk(current perk)
		*/
		JButton btnUpdatePerkInfo = new JButton("Update Perk");
		btnUpdatePerkInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateCurrentPerkInfo();
			}
		});
		btnUpdatePerkInfo.setBounds(246, 338, 124, 23);
		panelPerk.add(btnUpdatePerkInfo);
		
		/**
		//New Requirement//
		Add new blank req to list
		Update list
		Set current Req
		 */
		JButton btnNewRequirement = new JButton("New Requirement");
		btnNewRequirement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newRequirement();
			}
		});
		btnNewRequirement.setBounds(405, 310, 161, 23);
		panelPerk.add(btnNewRequirement);
		/**
		//Requirement Update//
		Update fields
		Set current req to existing req
		*/
		
		JButton btnUpdateRequirementInfo = new JButton("Update");
		btnUpdateRequirementInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateCurrentRequirmentInfo();
			}
		});		
		btnUpdateRequirementInfo.setBounds(576, 338, 89, 23);
		panelPerk.add(btnUpdateRequirementInfo);
		
		JButton btnDeleteRequirement = new JButton("Delete Requirement");
		btnDeleteRequirement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteCurrentRequirement();
			}
		});
		btnDeleteRequirement.setBounds(405, 338, 161, 23);
		panelPerk.add(btnDeleteRequirement);
		spinnerPointCost.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		
		
		spinnerPointCost.setBounds(246, 137, 29, 20);
		panelPerk.add(spinnerPointCost);
		
		JPanel panelSkill = new JPanel();
		panelSkill.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				updateSkillList();
			}
		});
		tabbedPane.addTab("Skill", null, panelSkill, null);
		
		JLabel lblNewLabel = new JLabel("Skill Name");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(150, 39, 65, 14);
		
		txtSkillName = new JTextField();
		txtSkillName.setBounds(225, 36, 130, 20);
		txtSkillName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Object Name");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(365, 69, 76, 14);
		
		JLabel lblNewLabel_2 = new JLabel("Experience");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(375, 100, 64, 14);
		
		txtSkillObjectName = new JTextField();
		txtSkillObjectName.setBounds(451, 66, 145, 20);
		txtSkillObjectName.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 130, 316);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(225, 67, 130, 260);
		scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JLabel lblExperience = new JLabel("Experience");
		lblExperience.setHorizontalAlignment(SwingConstants.RIGHT);
		lblExperience.setBounds(150, 69, 65, 14);
		
		JButton btnNewSkill = new JButton("New");
		btnNewSkill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newSkill();
			}
		});
		btnNewSkill.setBounds(10, 338, 63, 23);
		buttonGroup.add(btnNewSkill);
		listSkill.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		listSkill.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				updateCurrentSkill();
			}
		});
		panelSkill.setLayout(null);
		listSkill.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public Object getElementAt(int index) {
				return values[index];
			}
			public int getSize() {
				return values.length;
			}
		});
		listSkill.setSelectedIndex(0);
		scrollPane.setViewportView(listSkill);
		panelSkill.add(scrollPane);
		
		JLabel lblNewLabel_3 = new JLabel("Skill Name");
		scrollPane.setColumnHeaderView(lblNewLabel_3);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panelSkill.add(lblNewLabel);
		panelSkill.add(lblExperience);
		panelSkill.add(scrollPane_2);
		
		
		listSkillExp.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				updateCurrentSkillExp();
			}
		});
		scrollPane_2.setViewportView(listSkillExp);
		listSkillExp.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public Object getElementAt(int index) {
				return values[index];
			}
			public int getSize() {
				return values.length;
			}
		});
		listSkillExp.setSelectedIndex(0);
		listSkillExp.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panelSkill.add(txtSkillName);
		panelSkill.add(lblNewLabel_1);
		panelSkill.add(lblNewLabel_2);
		panelSkill.add(txtSkillObjectName);
		panelSkill.add(btnNewSkill);
		
		JButton btnUpdateSkillInfo = new JButton("Update Skill");
		btnUpdateSkillInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateCurrentSkillInfo();
			}
		});
		btnUpdateSkillInfo.setBounds(235, 338, 120, 23);
		panelSkill.add(btnUpdateSkillInfo);
		
		JButton btnNewExp = new JButton("New Experience");
		btnNewExp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newExperience();
			}
		});
		btnNewExp.setBounds(449, 196, 147, 23);
		panelSkill.add(btnNewExp);
		
		JButton btnUpdateExpInfo = new JButton("Update");
		btnUpdateExpInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateCurrentSkillExpInfo();
			}
		});
		btnUpdateExpInfo.setBounds(449, 128, 147, 23);
		panelSkill.add(btnUpdateExpInfo);
		
		JButton btnDeleteSkill = new JButton("Delete");
		btnDeleteSkill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteCurrentSkill();
			}
		});
		btnDeleteSkill.setBounds(72, 338, 76, 23);
		buttonGroup.add(btnDeleteSkill);
		panelSkill.add(btnDeleteSkill);
		
		
		spinnerSkillType.setModel(new SpinnerListModel(new String[] {"Block", "Entity", "Item"}));
		spinnerSkillType.setBounds(225, 11, 130, 14);
		panelSkill.add(spinnerSkillType);
		
		JLabel lblNewLabel_4 = new JLabel("Skill Type");
		lblNewLabel_4.setBounds(136, 11, 79, 14);
		panelSkill.add(lblNewLabel_4);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JButton btnDeleteExperience = new JButton("Delete Experience");
		btnDeleteExperience.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteCurrentExperience();
			}
		});
		btnDeleteExperience.setBounds(449, 162, 147, 23);
		panelSkill.add(btnDeleteExperience);
		
		
		spinnerExpXP.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinnerExpXP.setBounds(449, 97, 29, 20);
		panelSkill.add(spinnerExpXP);
		
		JPanel panelRegistry = new JPanel();
		tabbedPane.addTab("Registry", null, panelRegistry, null);
		panelRegistry.setLayout(new BoxLayout(panelRegistry, BoxLayout.X_AXIS));
		
		JScrollPane scrollPaneRegBlock = new JScrollPane();
		panelRegistry.add(scrollPaneRegBlock);
		
		
		scrollPaneRegBlock.setViewportView(listRegBlock);
		
		JLabel lblBlockRegistry = new JLabel("Block Registry");
		lblBlockRegistry.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPaneRegBlock.setColumnHeaderView(lblBlockRegistry);
		
		JLabel label_1 = new JLabel("");
		panelRegistry.add(label_1);
		
		JLabel label_2 = new JLabel("");
		panelRegistry.add(label_2);
		
		JLabel label_3 = new JLabel("");
		panelRegistry.add(label_3);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panelRegistry.add(scrollPane_1);
		
		
		scrollPane_1.setViewportView(listRegEntity);
		
		JLabel lblEntityRegistry = new JLabel("Entity Registry");
		lblEntityRegistry.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane_1.setColumnHeaderView(lblEntityRegistry);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		panelRegistry.add(scrollPane_5);
		
		
		scrollPane_5.setViewportView(listRegItem);
		
		JLabel lblItemRegistry = new JLabel("Item Registry");
		lblItemRegistry.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane_5.setColumnHeaderView(lblItemRegistry);
		
		JLabel label = new JLabel("");
		panelRegistry.add(label);
		
		JPanel panelAbout = new JPanel();
		tabbedPane.addTab("About", null, panelAbout, null);
		
		JTextPane txtpnGeneralRulesrecommendationsFill = new JTextPane();
		txtpnGeneralRulesrecommendationsFill.setText("General rules/recommendations:\r\nFill out ALL fields\r\nPlan out your skills and perks before hand\r\n\r\nPerk rules:\r\nAll perks must have unique names\r\n\r\nRegistry:\r\nYou need to remove anything before the \":\" when using the name (it just tells you the source)\r\n\r\nSkill rules:\r\nUnique names are NOT required (this allows for more complex skills)");
		txtpnGeneralRulesrecommendationsFill.setEditable(false);
		panelAbout.add(txtpnGeneralRulesrecommendationsFill);
	}

	/**
	 * Launch the application.
	 */
	public static void main() {
		Config window = new Config();
		updatePerkList();
		updateSkillList();
		updateRegLists();
		window.frame.setVisible(true);
	}

	protected static void newExperience() {
		if (currentSkill != null){
		    if (currentSkill instanceof BlockSkill){
		        currentSkill.getExp().add(new BlockXP());
		    }
		    else if (currentSkill instanceof EntitySkill){
		        currentSkill.getExp().add(new EntityXP());
		    }
		    else if (currentSkill instanceof ItemSkill) {
		        currentSkill.getExp().add(new EntityXP());
		    }
		    updateSkillExpList();
		}
		
	}

	protected static void newPerk() {
		BlockPerk perk = new BlockPerk();
		perks.add(perk);
		updatePerkList();		
	}

	protected static void newRequirement() {
		if (currentPerk != null){
			Requirement req = new Requirement(Action.HAVE, "default_name", "");
			currentPerk.requirements.add(req);
			updateReqList();
		}		
	}

	protected static void newSkill() {
		BlockSkill skill = new BlockSkill();
		skills.add(skill);
		updateSkillList();
		
	}
	private static void recreatePerk(){
		switch (spinnerPerkType.getValue().toString()){
		case "Block":
			BlockPerk tempBlockPerk = new BlockPerk();
			tempBlockPerk.name = textPerkName.getText();
			tempBlockPerk.description = editorPerkDescription.getText();
			tempBlockPerk.pointCost = (int) spinnerPointCost.getValue();
			tempBlockPerk.requirements = currentPerk.requirements;
			perks.set(perks.indexOf(currentPerk),tempBlockPerk);
			if(blockList.contains(textPerkObject.getText())){
			    tempBlockPerk.blockName = textPerkObject.getText();
			    currentPerk = tempBlockPerk;
			}
			else
			{
			    JOptionPane.showMessageDialog(null, textPerkObject.getText() + " is a invalid object");
			}
			break;
		case "Entity":
			EntityPerk tempEntityPerk = new EntityPerk();
			tempEntityPerk.name = textPerkName.getText();
			tempEntityPerk.description = editorPerkDescription.getText();
			tempEntityPerk.pointCost = (int) spinnerPointCost.getValue();
	        if(entityList.contains(textPerkObject.getText())){
	            tempEntityPerk.entityName = textPerkObject.getText();
	        }
	        else
	        {
	            JOptionPane.showMessageDialog(null, textPerkObject.getText() + " is a invalid object");
	        }
			tempEntityPerk.requirements = currentPerk.requirements;
			perks.set(perks.indexOf(currentPerk),tempEntityPerk);
			currentPerk = tempEntityPerk;
			break;
		case "Item":
			ItemPerk tempItemPerk = new ItemPerk();
			tempItemPerk.name = textPerkName.getText();
			tempItemPerk.description = editorPerkDescription.getText();
			tempItemPerk.pointCost = (int) spinnerPointCost.getValue();
	        if(itemList.contains(textPerkObject.getText())){
	            tempItemPerk.itemName = textPerkObject.getText();
	        }
	        else
	        {
	            JOptionPane.showMessageDialog(null, textPerkObject.getText() + " is a invalid object");
	        }
			tempItemPerk.requirements = currentPerk.requirements;
			perks.set(perks.indexOf(currentPerk),tempItemPerk);
			currentPerk = tempItemPerk;
			break;
		case "RegexBlock":
		    RegexBlockPerk regexBlockPerk = new RegexBlockPerk();
		    regexBlockPerk.name = textPerkName.getText();
		    regexBlockPerk.description  = editorPerkDescription.getText();
		    regexBlockPerk.pointCost = (int) spinnerPointCost.getValue();
		    regexBlockPerk.searchQuery = textPerkObject.getText();
          break;
		case "RegexEntity":
		    RegexEntityPerk regexEntityPerk = new RegexEntityPerk();
		    regexEntityPerk.name = textPerkName.getText();
            regexEntityPerk.description  = editorPerkDescription.getText();
            regexEntityPerk.pointCost = (int) spinnerPointCost.getValue();
            regexEntityPerk.searchQuery = textPerkObject.getText();
	      break;
		case "RegexItem":
		    RegexItemPerk regexItemPerk = new RegexItemPerk();
		    regexItemPerk.name = textPerkName.getText();
            regexItemPerk.description  = editorPerkDescription.getText();
            regexItemPerk.pointCost = (int) spinnerPointCost.getValue();
            regexItemPerk.searchQuery = textPerkObject.getText();
	      break;
		case "Title":
		    TitlePerk title = new TitlePerk();
		    title.name = textPerkName.getText();
		    title.description  = editorPerkDescription.getText();
		    title.pointCost = (int) spinnerPointCost.getValue();
		    title.setTitle(textPerkObject.getText());
	      break;
		}		
	}

	private static void recreateSkill() {
		switch (spinnerSkillType.getValue().toString()){
		case "Block":
			BlockSkill tempSkill = new BlockSkill();
			tempSkill.name = txtSkillName.getText();
			tempSkill.setExp(new ArrayList<BlockXP>());
			skills.set(skills.indexOf(currentSkill),tempSkill);
			currentSkill = tempSkill;
			break;
		case "Entity":
			EntitySkill tempEntitySkill = new EntitySkill();
			tempEntitySkill.setExp(new ArrayList<EntityXP>());
			tempEntitySkill.name = txtSkillName.getText();
			skills.set(skills.indexOf(currentSkill),tempEntitySkill);
			currentSkill = tempEntitySkill;
			break;
		case "Item":
			ItemSkill tempItemSkill = new ItemSkill();
			tempItemSkill.setExp(new ArrayList<ItemXP>());
			tempItemSkill.name = txtSkillName.getText();
			skills.set(skills.indexOf(currentSkill),tempItemSkill);
			currentSkill = tempItemSkill;
			break;
		}
		
	}

	protected static void updateCurrentPerk() {
		if (listPerk.getSelectedIndex() > -1){
		    currentPerk = perks.get(listPerk.getSelectedIndex());
		    textPerkName.setText(currentPerk.name);
	        editorPerkDescription.setText(currentPerk.description);
	        spinnerPointCost.setValue(currentPerk.pointCost);
		}
		else
		{
			currentPerk = null;
			textPerkName.setText("");
	        editorPerkDescription.setText("");
	        spinnerPointCost.setValue(0);
		}
		
		
		if (currentPerk instanceof BlockPerk){
			BlockPerk temp = (BlockPerk) currentPerk;
			spinnerPerkType.setValue("Block");
		    textPerkObject.setText(temp.blockName);
		    temp = null;
		} else if (currentPerk instanceof ItemPerk){
			spinnerPerkType.setValue("Item");
			ItemPerk temp = (ItemPerk) currentPerk;
			textPerkObject.setText(temp.itemName);
			temp = null;
		}
		else if (currentPerk instanceof EntityPerk){
			spinnerPerkType.setValue("Entity");
			EntityPerk temp = (EntityPerk) currentPerk;
			textPerkObject.setText(temp.entityName);
			temp = null;
		} else if (currentPerk == null){
			spinnerPerkType.setValue("Block");
		    textPerkObject.setText("");
		}		
		updateReqList();
		updateCurrentRequirment();
	}

	protected static void updateCurrentPerkInfo() {
		recreatePerk();
		updatePerkList();
	}

	protected static void updateCurrentRequirment() {
		if(listRequirement.getSelectedIndex() > -1){
			currentReq = currentPerk.requirements.get(listRequirement.getSelectedIndex());
			spinnerPerkAction.setValue(currentReq.action.toString());
			textPerkReqName.setText(currentReq.name);
			textPerkExtraData.setText(currentReq.extraData);
		}else{
			spinnerPerkAction.setValue("HAVE");
			textPerkReqName.setText("");
			textPerkExtraData.setText("");
		}		

	}

	protected static void updateCurrentRequirmentInfo() {
		if (currentReq != null){
			currentReq.action = Action.valueOf(spinnerPerkAction.getValue().toString());
			currentReq.extraData = textPerkExtraData.getText();
			currentReq.name = textPerkReqName.getText();
			updateReqList();
		}
		
	}

	protected static void updateCurrentSkill() {
		if (listSkill.getSelectedIndex() > -1){
			currentSkill = skills.get(listSkill.getSelectedIndex());			
			if (currentSkill instanceof BlockSkill || currentSkill == null){
				spinnerSkillType.setValue("Block");
			}else if(currentSkill instanceof ItemSkill){
				spinnerSkillType.setValue("Item");
			}else if(currentSkill instanceof EntitySkill){
				spinnerSkillType.setValue("Entity");
			}
			txtSkillName.setText(currentSkill.name);
		}else{
			currentSkill = null;
			txtSkillName.setText("");
		}
		
		updateSkillExpList();
	}
	protected static void updateCurrentSkillExp() {
	    if (currentSkill != null && listSkillExp.getSelectedIndex() > -1){
	        currentExp = (XPGain) currentSkill.getExp().get(listSkillExp.getSelectedIndex());
	        if (currentExp != null){
	            txtSkillObjectName.setText(currentExp.getName());
	            spinnerExpXP.setValue(currentExp.getXp());
	        }
	        else{
	            txtSkillObjectName.setText("");
                spinnerExpXP.setValue(0);
	        }
	    }
	}

	protected static void updateCurrentSkillExpInfo() {
		if (currentExp != null){
			currentExp.setXp((int) spinnerExpXP.getValue());
	         if(itemModel.contains(txtSkillObjectName.getText()) || 
	            blockModel.contains(txtSkillObjectName.getText()) ||
	            entityModel.contains(txtSkillObjectName.getText()) ){
	                 currentExp.setName(txtSkillObjectName.getText());
	         }
	         else
	         {
	             JOptionPane.showMessageDialog(null, textPerkObject.getText() + " is a invalid object");
	         }			
			updateSkillExpList();
			}		
	}
	protected static void updateCurrentSkillInfo() {
		if(currentSkill != null){			
			if (currentSkill instanceof BlockSkill && spinnerSkillType.getValue() != "Block"){
				recreateSkill();					
			} else if (currentSkill instanceof EntitySkill && spinnerSkillType.getValue() != "Entity"){
				recreateSkill();
			} else if (currentSkill instanceof ItemSkill && spinnerSkillType.getValue() != "Item"){
				recreateSkill();
			}else{
				currentSkill.name = txtSkillName.getText();
			}
			updateSkillList();
		}
		
	}

	private static void updatePerkList(){
		DefaultListModel model = new DefaultListModel();
		for (Perk perk: perks){
			model.addElement(perk.name);
		}
		listPerk.setModel(model);
		listPerk.updateUI();
	}

	private static void updateRegLists(){
	    ArrayList<String> unsortedItemEntries = new ArrayList<String>();	    
	    itemModel = new DefaultListModel();
		Iterator it = Item.itemRegistry.getKeys().iterator();
		while (it.hasNext()){
		    String current = it.next().toString();
		    unsortedItemEntries.add(current);
		    itemList.add(current.substring(current.lastIndexOf(":")));
		}
		Collections.sort(unsortedItemEntries);
		for (String string : unsortedItemEntries){
		    itemModel.addElement(string);
		}
		
        ArrayList<String> unsortedBlockEntries = new ArrayList<String>();       
        blockModel = new DefaultListModel();
        it = Block.blockRegistry.getKeys().iterator();
        while (it.hasNext()){
            String current = it.next().toString();
            unsortedBlockEntries.add(current);
            blockList.add(current.substring(current.lastIndexOf(":")));
        }
        Collections.sort(unsortedBlockEntries);
        for (String string : unsortedBlockEntries){
            blockModel.addElement(string);
        }
        
        ArrayList<String> unsortedEntityEntries = new ArrayList<String>();      
        entityModel = new DefaultListModel();
        it = EntityList.func_151515_b().iterator();
        while (it.hasNext()){
            String current = it.next().toString();
            unsortedEntityEntries.add(current);
            entityList.add(current);
        }
        Collections.sort(unsortedEntityEntries);
        for (String string : unsortedEntityEntries){
            entityModel.addElement(string);
        }
		listRegBlock.setModel(blockModel);
		listRegBlock.updateUI();
		listRegEntity.setModel(entityModel);
		listRegEntity.updateUI();
		listRegItem.setModel(itemModel);
		listRegItem.updateUI();
	}


    private static void updateReqList() {
		DefaultListModel model = new DefaultListModel();
		if ( currentPerk != null && currentPerk.getRequirements() != null){
    		ArrayList<Requirement> temp = currentPerk.getRequirements();
    		for (Requirement req: temp){
    			model.addElement(req.name);
    		}
		}
		listRequirement.setModel(model);
		listRequirement.updateUI();
		
		
	}


    private static void updateSkillExpList() {
		DefaultListModel model = new DefaultListModel();
		if (currentSkill != null){
			ArrayList<XPGain> local = currentSkill.getExp();		
			for (int i = 0; i < local.size(); i++){
				model.addElement(local.get(i).getName());
			}
		}
		listSkillExp.setModel(model);
		listSkillExp.updateUI();		
	}


    private static void updateSkillList(){
		DefaultListModel model = new DefaultListModel();
		for (Skill skill: skills){
			model.addElement(skill.name);
		}
		listSkill.setModel(model);
		listSkill.updateUI();
	}
}
