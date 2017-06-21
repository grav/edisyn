/***
    Copyright 2017 by Sean Luke
    Licensed under the Apache License version 2.0
*/

package edisyn.synth;

import edisyn.*;
import edisyn.gui.*;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.border.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

/**
   A patch editor for the Waldorf Microwave XT.  Does not deal with Multi mode, global parameters,
   modifying wavetables, or uploading samples.  Only Single mode patches.
        
   @author Sean Luke
*/

public class MicrowaveXTMulti extends Synth
    {
    // NOTES:
    // Sound Arp?
    // "Alternating"?
    // Is the dump length really the same as the dump length for the XT?


    /// Various collections of parameter names for pop-up menus
        
    static final String[] KEYS = new String[] { "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B" };
    static final String[] BANKS = new String[] { "A", "B" };
    static final String[] PAN_MOD = new String[] { "Off", "On", "Inverse" };
    static final String[] ARPEGGIATOR_ACTIVE = new String[] { "Off", "On", "Hold", "Sound Arp" };
    static final String[] ARP_CLOCK = new String[] { "1/1", "1/2 .", "1/2 T", "1/2", "1/4 .", "1/4 T", "1/4", "1/8 .", "1/8 T", "1/8", "1/16 .", "1/16 T", "1/16", "1/32 .", "1/32 T", "1/32"};
    static final String[] ARPEGGIATOR_DIRECTION = new String[] { "Up", "Down", "Alternating", "Random" };  // is it Alternating?
	static final String[] ARPEGGIATOR_ORDER = new String[] { "By Note", "By Note Reversed", "As Played", "As Played Reversed" };
	static final String[] ARPEGGIATOR_VELOCITY = new String[] { "Root Note", "Last Note" }; 
    static final String[] MIDI_SEND = new String[] { "Global", "Specific" };
        
    public MicrowaveXTMulti()
        {
        for(int i = 0; i < allParameters.length; i++)
            {
            allParametersToIndex.put(allParameters[i], Integer.valueOf(i));
            }
                
        setSendsAllParametersInBulk(true);
        
        setLayout(new BorderLayout());
                
        JTabbedPane tabs = new JTabbedPane();
                
        JComponent soundPanel = new SynthPanel();
        VBox vbox = new VBox();
        vbox.add(addNameGlobal(Style.COLOR_GLOBAL));
        vbox.add(addMultiData(Style.COLOR_A));

		for(int i = 1; i < 3; i++)
			{
			vbox.add(addInstrument(i, Style.COLOR_B));
			}
                
        soundPanel.add(vbox, BorderLayout.CENTER);
        tabs.addTab("Multi and Instruments 1 - 2", soundPanel);

        soundPanel = new SynthPanel();
        vbox = new VBox();

		for(int i = 3; i < 6; i++)
			{
			vbox.add(addInstrument(i, Style.COLOR_B));
			}
                
        soundPanel.add(vbox, BorderLayout.CENTER);
        tabs.addTab("Instruments 3 - 5", soundPanel);

        soundPanel = new SynthPanel();
        vbox = new VBox();

		for(int i = 6; i < 9; i++)
			{
			vbox.add(addInstrument(i, Style.COLOR_B));
			}
                
        soundPanel.add(vbox, BorderLayout.CENTER);
        tabs.addTab("Instruments 6 - 8", soundPanel);

		tabs.addTab("About", new HTMLBrowser(this.getClass().getResourceAsStream("MicrowaveXTMulti.html")));

        add(tabs, BorderLayout.CENTER);

        model.set("name", "Init            ");  // has to be 16 long
        
        //loadDefaults();
        }
                
                
    public String getDefaultResourceFileName() { return "MicrowaveXTMulti.init"; }

    public boolean gatherInfo(String title, Model change)
        {
        JTextField number = new JTextField("" + (model.get("number", 0) + 1), 3);

        JTextField id = new JTextField("" + model.get("id", 0), 3);
                
        while(true)
            {
            boolean result = doMultiOption(this, new String[] { "Patch Number", "Device ID" }, 
                new JComponent[] { number, id }, title, "Enter the Patch number and Device ID.");
                
            if (result == false) 
                return false;
                                
            int n;
            try { n = Integer.parseInt(number.getText()); }
            catch (NumberFormatException e)
                {
                JOptionPane.showMessageDialog(null, "The Patch Number must be an integer 1 ... 128", title, JOptionPane.ERROR_MESSAGE);
                continue;
                }
            if (n < 1 || n > 128)
                {
                JOptionPane.showMessageDialog(null, "The Patch Number must be an integer 1 ... 128", title, JOptionPane.ERROR_MESSAGE);
                continue;
                }
                                
            int i;
            try { i = Integer.parseInt(id.getText()); }
            catch (NumberFormatException e)
                {
                JOptionPane.showMessageDialog(null, "The Device ID must be an integer 0 ... 127", title, JOptionPane.ERROR_MESSAGE);
                continue;
                }
            if (i < 0 || i > 127)
                {
                JOptionPane.showMessageDialog(null, "The Device ID must be an integer 0 ... 127", title, JOptionPane.ERROR_MESSAGE);
                continue;
                }
                        
            change.set("number", n - 1);
            change.set("id", i);
                        
            return true;
            }
        }


    /** Add the global patch category (name, id, number, etc.) */
    public JComponent addNameGlobal(Color color)
        {
        Category globalCategory = new Category("Waldorf Microwave II/XT/XTk Multi", color);
                
        JComponent comp;
        String[] params;
        HBox hbox = new HBox();
                
        VBox vbox = new VBox();
        comp = new StringComponent("Patch Name", this, "name", 16, "Name must be up to 16 ASCII characters.")
            {
            public boolean isValid(String val)
                {
                if (val.length() > 16) return false;
                for(int i = 0 ; i < val.length(); i++)
                    {
                    char c = val.charAt(i);
                    if (c < 32 || c > 127) return false;
                    }
                return true;
                }
                                
            public void update(String key, Model model)
                {
                super.update(key, model);
                updateTitle();
                }
            };
        model.setImmutable("name", true);
        vbox.add(comp);
        hbox.add(vbox);
                
        comp = new LabelledDial("Number", this, "number", color, 0, 127, -1);
        model.setImmutable("number", true);
        hbox.add(comp);

        comp = new LabelledDial("Device ID", this, "id", color, 0, 127)
        	{
            public String map(int val)
                {
                if (val == 127)
                	return "All";
                else return "" + val;
                }
        	};
        model.setImmutable("id", true);
        hbox.add(comp);
        
        hbox.add(Strut.makeHorizontalStrut(17));

        globalCategory.add(hbox, BorderLayout.WEST);
        return globalCategory;
        }

        

    public JComponent addMultiData(Color color)
        {
        Category category = new Category("General", color);
                
        JComponent comp;
        String[] params;
        HBox hbox = new HBox();
        VBox vbox = new VBox();
    
    	params = MIDI_SEND;
        comp = new Chooser("MIDI Send [XTk]", this, "midisend", params);
        vbox.add(comp);
        hbox.add(vbox);

        comp = new LabelledDial("Volume", this, "volume", color, 0, 127);
        hbox.add(comp);

        comp = new LabelledDial("Control W", this, "controlw", color, 0, 127);
        hbox.add(comp);

        comp = new LabelledDial("Control X", this, "controlx", color, 0, 127);
        hbox.add(comp);

        comp = new LabelledDial("Control Y", this, "controly", color, 0, 127);
        hbox.add(comp);

        comp = new LabelledDial("Control Z", this, "controlz", color, 0, 127);
        hbox.add(comp);


    	comp = new LabelledDial("Arp Tempo", this, "arptempo", color, 1, 127)
    		{
            public String map(int val)
                {
                if (val == 1)
                	return "Extern";
                else
                	{
                	return "" + (50 + (val - 2) * 2);
                	}
                }
    		};
        //((LabelledDial)comp).setSecondLabel("Tempo");
        hbox.add(comp);
        
        category.add(hbox, BorderLayout.WEST);
        return category;
        }


    public JComponent addInstrument(int inst, Color color)
        {
        Category category = new Category("Instrument " + inst, color);
                
        JComponent comp;
        String[] params;
        HBox hbox = new HBox();
        VBox vbox = new VBox();

       	comp = new LabelledDial("Bank", this, "bank" + inst, color, 0, 1)
            {
            public String map(int val)
                {
                String[] vals = BANKS;
                return vals[val];
                }
            };
        model.setImmutable("bank" + inst, true);
        ((LabelledDial)comp).setSecondLabel(" ");
        vbox.add(comp);


        comp = new LabelledDial("Number", this, "number" + inst, color, 0, 127, -1);
        model.setImmutable("number" + inst, true);
        vbox.add(comp);
        
        hbox.add(vbox);

       	params = PAN_MOD;
       	vbox = new VBox();

        comp = new Chooser("Pan Mod", this, "arpdirectionection", params);
        vbox.add(comp);

        comp = new CheckBox("Active", this, "status" + inst);
    	vbox.add(comp);    

     	comp = new CheckBox("Sub Out", this, "output" + inst);
    	vbox.add(comp);    

        comp = new CheckBox("Reset Arp on Start", this, "arpreset" + inst);
        vbox.add(comp);
		hbox.add(vbox);
		
    	comp = new CheckBox("MIDI Send [XTk]", this, "midisend");
    	vbox.add(comp);    

		
		vbox = new VBox();
        params = ARPEGGIATOR_ACTIVE;
        comp = new Chooser("Arp Active", this, "arp" + inst, params);
        vbox.add(comp);
        
        params = ARPEGGIATOR_DIRECTION;
        comp = new Chooser("Arp Direction", this, "arpdirection" + inst, params);
        vbox.add(comp);
        
        params = ARPEGGIATOR_ORDER;
        comp = new Chooser("Arp Note Order", this, "arpnoteorder" + inst, params);
        vbox.add(comp);
        
        params = ARPEGGIATOR_VELOCITY;
        comp = new Chooser("Arp Velocity", this, "arpvelocity" + inst, params);
        vbox.add(comp);

        hbox.add(vbox);
        
        
       	vbox = new VBox();
        HBox hbox2 = new HBox();

        comp = new LabelledDial("Volume", this, "volume" + inst, color, 0, 127);
        hbox2.add(comp);

        comp = new LabelledDial("Transpose", this, "transpose" + inst, color, 16, 112, 64);
        hbox2.add(comp);

        comp = new LabelledDial("Detune", this, "detune" + inst, color, 0, 127, 64);
        hbox2.add(comp);

        comp = new LabelledDial("Panning", this, "panning"  + inst, color, 0, 127, 64)
        	{
            public String map(int val)
                {
                if ((val - 64) < 0) return "L " + Math.abs(val - 64);
                else if ((val - 64) > 0) return "R " + (val - 64);
                else return "--";
                }
            };
        hbox2.add(comp);
        
    	comp = new LabelledDial("Highest", this, "hivel" + inst, color, 1, 127);
        ((LabelledDial)comp).setSecondLabel("Velocity");
        hbox2.add(comp);

    	comp = new LabelledDial("Highest", this, "hikey" + inst, color, 1, 127)
    		{
            public String map(int val)
                {
                return KEYS[val % 12] + (val / 12 - 2);  // note integer division
                }
    		};
        ((LabelledDial)comp).setSecondLabel("Key");
        hbox2.add(comp);

		vbox.add(hbox2);
		hbox2 = new HBox();
		
    	comp = new LabelledDial("Arp", this, "arpclock" + inst, color, 0, 15)
        	{
            public String map(int val)
                {
                return ARP_CLOCK[val];
                }
        	};
        ((LabelledDial)comp).setSecondLabel("Clock");
        hbox2.add(comp);

    	comp = new LabelledDial("Arp", this, "arprange" + inst, color, 1, 10);
        ((LabelledDial)comp).setSecondLabel("Range");
        hbox2.add(comp);

        comp = new LabelledDial("Arp", this, "arppattern" + inst, color, 0, 16)
        	{
            public String map(int val)
                {
                if (val == 0)
                	return "Off";
                else if (val == 1)
                	return "User";
                else return "" + (val - 1);
                }
        	};
        ((LabelledDial)comp).setSecondLabel("Pattern");
        hbox2.add(comp);

		comp = new LabelledDial("Arp", this, "arpnotesout" + inst, color, 0, 18)
        	{
            public String map(int val)
                {
                if (val == 0)
                	return "Off";
                else if (val == 17)
                	return "Inst";
                else if (val == 18)
                	return "Global";
                else return "" + (val);
                }
        	};
        ((LabelledDial)comp).setSecondLabel("Notes Out");
        hbox2.add(comp);
        
    	comp = new LabelledDial("Lowest", this, "lowvel" + inst, color, 1, 127);
        ((LabelledDial)comp).setSecondLabel("Velocity");
        hbox2.add(comp);

    	comp = new LabelledDial("Lowest", this, "lowkey" + inst, color, 1, 127)
    		{
            public String map(int val)
                {
                return KEYS[val % 12] + (val / 12 - 2);  // note integer division
                }
    		};
        ((LabelledDial)comp).setSecondLabel("Key");
        hbox2.add(comp);


        vbox.add(hbox2);
        hbox.add(vbox);

        category.add(hbox, BorderLayout.WEST);
        return category;
        }



    /** Map of parameter -> index in the allParameters array. */
    HashMap allParametersToIndex = new HashMap();


    /** List of all Waldorf parameters in order.  "-" is a reserved (unused and thus unnamed) parameter. */

    /// * indicates parameters which must be handled specially due to packing
    /// that Waldorf decided to do.  :-(

    final static String[] allParameters = new String[/*256*/] 
    {
    "volume",
    "controlw",                   
    "controlx",
    "controly",
    "controlz",
    "arptempo",
    "midisend",
    "-",
    "-",
    "-",
    "-",
    "-",
    "-",
    "-",                   
    "-",
    "-",
    "name",         // *
    "name",         // *
    "name",         // *
    "name",         // *
    "name",         // *
    "name",         // *
    "name",         // *
    "name",         // *
    "name",         // *
    "name",         // *
    "name",         // *
    "name",         // *
    "name",         // *
    "name",         // *
    "name",         // *
    "name",         // *
    
    
    "bank1",
    "number1",
    "channel1",
    "volume1",
    "transpose1",
    "detune1",
    "output1",
    "status1",
    "panning1",
    "panmod1",
    "-",
    "-",
    "lowvel1",
    "hivel1",
    "lowkey1",
    "hikey1",
    "arp1",
    "arpclock1",
    "arprange1",
    "arppattern1",
    "arpdirection1",
    "arporder1",
    "arpvel1",
    "arpreset1",
    "arpnotesout1",
    "-",
    "midisend1",
    "-",

    "bank2",
    "number2",
    "channel2",
    "volume2",
    "transpose2",
    "detune2",
    "output2",
    "status2",
    "panning2",
    "panmod2",
    "-",
    "-",
    "lowvel2",
    "hivel2",
    "lowkey2",
    "hikey2",
    "arp2",
    "arpclock2",
    "arprange2",
    "arppattern2",
    "arpdirection2",
    "arporder2",
    "arpvel2",
    "arpreset2",
    "arpnotesout2",
    "-",
    "midisend2",
    "-",


    "bank3",
    "number3",
    "channel3",
    "volume3",
    "transpose3",
    "detune3",
    "output3",
    "status3",
    "panning3",
    "panmod3",
    "-",
    "-",
    "lowvel3",
    "hivel3",
    "lowkey3",
    "hikey3",
    "arp3",
    "arpclock3",
    "arprange3",
    "arppattern3",
    "arpdirection3",
    "arporder3",
    "arpvel3",
    "arpreset3",
    "arpnotesout3",
    "-",
    "midisend3",
    "-",


    "bank4",
    "number4",
    "channel4",
    "volume4",
    "transpose4",
    "detune4",
    "output4",
    "status4",
    "panning4",
    "panmod4",
    "-",
    "-",
    "lowvel4",
    "hivel4",
    "lowkey4",
    "hikey4",
    "arp4",
    "arpclock4",
    "arprange4",
    "arppattern4",
    "arpdirection4",
    "arporder4",
    "arpvel4",
    "arpreset4",
    "arpnotesout4",
    "-",
    "midisend4",
    "-",


    "bank5",
    "number5",
    "channel5",
    "volume5",
    "transpose5",
    "detune5",
    "output5",
    "status5",
    "panning5",
    "panmod5",
    "-",
    "-",
    "lowvel5",
    "hivel5",
    "lowkey5",
    "hikey5",
    "arp5",
    "arpclock5",
    "arprange5",
    "arppattern5",
    "arpdirection5",
    "arporder5",
    "arpvel5",
    "arpreset5",
    "arpnotesout5",
    "-",
    "midisend5",
    "-",


    "bank6",
    "number6",
    "channel6",
    "volume6",
    "transpose6",
    "detune6",
    "output6",
    "status6",
    "panning6",
    "panmod6",
    "-",
    "-",
    "lowvel6",
    "hivel6",
    "lowkey6",
    "hikey6",
    "arp6",
    "arpclock6",
    "arprange6",
    "arppattern6",
    "arpdirection6",
    "arporder6",
    "arpvel6",
    "arpreset6",
    "arpnotesout6",
    "-",
    "midisend6",
    "-",


    "bank7",
    "number7",
    "channel7",
    "volume7",
    "transpose7",
    "detune7",
    "output7",
    "status7",
    "panning7",
    "panmod7",
    "-",
    "-",
    "lowvel7",
    "hivel7",
    "lowkey7",
    "hikey7",
    "arp7",
     "arpclock7",
   "arprange7",
    "arppattern7",
    "arpdirection7",
    "arporder7",
    "arpvel7",
    "arpreset7",
    "arpnotesout7",
    "-",
    "midisend7",
    "-",


    "bank8",
    "number8",
    "channel8",
    "volume8",
    "transpose8",
    "detune8",
    "output8",
    "status8",
    "panning8",
    "panmod8",
    "-",
    "-",
    "lowvel8",
    "hivel8",
    "lowkey8",
    "hikey8",
    "arp8",
    "arpclock8",
    "arprange8",
    "arppattern8",
    "arpdirection8",
    "arporder8",
    "arpvel8",
    "arpreset8",
    "arpnotesout8",
    "-",
    "midisend8",
    "-"

    };



    public byte[] emit(String key)
        {
        if (key.equals("id")) return new byte[0];  // this is not emittable
        if (key.equals("number")) return new byte[0];  // this is not emittable
        byte DEV = (byte)model.get("id", 0);
		
		if (key.equals("name"))
            {
            byte[] bytes = new byte[16 * 9];
            String name = model.get(key, "Init            ");  // just to be safe, has to be 16 long
            for(int i = 0; i < 16; i++)
                {
                byte c = 0x20;  // space
                if (i < name.length())
                	c = (byte)(name.charAt(i));
                int index = i;
                byte PP = (byte)(index & 127);
                byte XX = c;
                byte LL = 0x20;
                if (index > 32)
                	{
                	LL = (byte)((index - 32) / 28);  // hope that's right
                	}
                	
                // In Section 2.23 of sysex document, MULP is declared to be 0x20, but then in the
                // format example, it's written as 0x21
                byte[] b = new byte[] { (byte)0xF0, 0x3E, 0x0E, DEV, 0x20, LL, PP, XX, (byte)0xF7 };
                System.arraycopy(b, 0, bytes, i * 9, 9);
                }
            return bytes;
            }
        else
            {
            int index = ((Integer)(allParametersToIndex.get(key))).intValue();
            byte PP = (byte)(index & 127);
            byte XX = (byte)model.get(key, 0);
			byte LL = 0x20;
			if (index > 32)
				{
				LL = (byte)((index - 32) / 28);  // hope that's right
				}
           return new byte[] { (byte)0xF0, 0x3E, 0x0E, DEV, 0x20, LL, PP, XX, (byte)0xF7 };
            }
        }
    
    
    
    
    public byte[] emit(Model tempModel, boolean toWorkingMemory)
        {
        if (tempModel == null)
            tempModel = getModel();
        byte DEV = (byte) tempModel.get("id", 0);
        byte NN = (byte) tempModel.get("number", 0);
        byte BB = 0x0;
        if (toWorkingMemory) { BB = 0x20; NN = 0x0; }
        
        String name = model.get("name", "Init Sound V1.1 ");  // has to be 16 long
                                
        byte[] bytes = new byte[256];
        
        for(int i = 0; i < 256; i++)
            {
            String key = allParameters[i];

			if (key.equals("name"))
				{
            	if (i - 16 >= name.length())
            		bytes[i] = 0x20;  // space
            	else
	          	  	bytes[i] = (byte)(name.charAt(i - 16));
				}
			else
				{
				bytes[i] = (byte)(model.get(key, 0));
				}
			}
			
		// In Section 2.23 of sysex document, MULD is declared to be 0x21, but then in the
		// format example, it's written as 0x11
		
        byte[] full = new byte[getExpectedSysexLength()];
        full[0] = (byte)0xF0;
        full[1] = 0x3E;
        full[2] = 0x0E;
        full[3] = DEV;
        full[4] = 0x21;
        full[5] = BB;
        full[6] = NN;
        // next comes the MDATA, followed by all 8 IDATA slots
        System.arraycopy(bytes, 0, full, 7, bytes.length);
        full[263] = produceChecksum(BB, NN, bytes);
        full[264] = (byte)0xF7;

        return full;
        }


    /** Generate a Waldorf checksum of the data bytes */
    byte produceChecksum(byte bb, byte nn, byte[] bytes)
        {
        //      From the sysex document:
        //
        //      "Sum of all databytes truncated to 7 bits.
        //  The addition is done in 8 bit format, the result is    
        //  masked to 7 bits (00h to 7Fh). A checksum of 7Fh is
        //  always accepted as valid.
        //  IMPORTANT: the MIDI status-bytes as well as the 
        //  ID's are not used for computing the checksum."
                
        byte b = bb;
        b += nn;  // I *think* signed will work
        for(int i = 0; i < bytes.length; i++)
            b += bytes[i];
        
        b = (byte)(b & (byte)127);
        
        return b;
        }


    public byte[] requestDump(Model tempModel)
        {
        if (tempModel == null)
            tempModel = getModel();
        byte DEV = (byte)tempModel.get("id", 0);
        byte BB = 0;  // only 1 bank
        byte NN = (byte)tempModel.get("number", 0);
        //(BB + NN)&127 is checksum
        return new byte[] { (byte)0xF0, 0x3E, 0x0E, DEV, 0x11, BB, NN, (byte)((BB + NN)&127), (byte)0xF7 };
        }
        
    public byte[] requestCurrentDump(Model tempModel)
        {
        if (tempModel == null)
            tempModel = getModel();
        byte DEV = (byte)tempModel.get("id", 0);
        //(0x75 + 0x00)&127 is checksum
        return new byte[] { (byte)0xF0, 0x3E, 0x0E, DEV, 0x11, 0x20, 0x00, (byte)((0x20 + 0x00)&127), (byte)0xF7 };
        }

    public static boolean recognize(byte[] data)
        {
        boolean v = (data[0] == (byte)0xF0 &&
            data[1] == (byte)0x3E &&
            data[2] == (byte)0x0E &&
            data.length == EXPECTED_SYSEX_LENGTH);
        return v;
        }
        
    
    public static final int EXPECTED_SYSEX_LENGTH = 265;
    public int getExpectedSysexLength() { return 265; }
        
        
    /** Verify that all the parameters are within valid values, and tweak them if not. */
    void revise()
        {
        for(int i = 0; i < allParameters.length; i++)
            {
            String key = allParameters[i];
            if (!model.isString(key))
                {
                if (model.minExists(key) && model.maxExists(key))
                    {
                    int val = model.get(key, 0);
                    if (val < model.getMin(key))
                        { model.set(key, model.getMin(key)); System.err.println("Warning: Revised " + key + " from " + val + " to " + model.get(key, 0));}
                    if (val > model.getMax(key))
                        { model.set(key, model.getMax(key)); System.err.println("Warning: Revised " + key + " from " + val + " to " + model.get(key, 0));}
                    }
                }
            }
        // handle "name" specially
        StringBuffer name = new StringBuffer(model.get("name", "Init Sound V1.1 "));  // has to be 16 long
        for(int i = 0; i < name.length(); i++)
            {
            char c = name.charAt(i);
            if (c < 32 || c > 127)
                { name.setCharAt(i, (char)32); System.err.println("Warning: Revised name from \"" + model.get("name", "Init Sound V1.1 ") + "\" to \"" + name.toString() + "\"");}
            }
        model.set("name", name.toString());
        }
        




	public void setParameterByIndex(int i, byte b)
		{
		String key = allParameters[i];
		if (key.equals("-"))
			{
			// do nothing
			}
		else if (i >= 16 && i < 32)  // name
			{
			try 
				{
				String name = model.get("name", "Init            ");
				byte[] str = name.getBytes("US-ASCII");
				byte[] newstr = new byte[] { 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20 };
				System.arraycopy(str, 0, newstr, 0, 16);
				newstr[i - 16] = b;
				model.set("name", new String(newstr, "US-ASCII"));
				}
			catch (UnsupportedEncodingException e)
				{
				e.printStackTrace();
				}
			}
		else
			{
			model.set(key, b);
			}
		}

        
    public void parseParameter(byte[] data)
		{
		int index = -1;
		byte b = 0;
		
                // In Section 2.23 of sysex document, MULP is declared to be 0x20, but then in the
                // format example, it's written as 0x21
                
                // Section 2.23 also has incorrect index labels in its format example (skipping index 6)
                // There are thus only 9 bytes

		// is it a sysex parameter change?
        if (data[0] == (byte)0xF0 &&
           	data[1] == (byte)0x3E &&
            data[2] == (byte)0x0E &&		// Microwave
            // filter by ID?  Presently I'm not
            data[4] == (byte)0x20 &&
            data[5] == 0x00 &&  // only Sound Mode Edit Bufer
            data.length == 9)
            {
            int hi = (int)(data[6] & 127);
            int lo = (int)(data[7] & 127);
             
            index = (hi << 7) | (lo);
            b = (byte)(data[8] & 127);
            setParameterByIndex(index, b);
            }
        else
        	{
        	// we'll put CC here later
        	}
        revise();
		}
        

    public boolean parse(byte[] data)
        {
        boolean retval = true;
        model.set("id", data[3]);
        if (data[5] < 8)  // or < 1 ? Anyway, otherwise it's probably just local patch data.  Too bad they do this. :-(
        	{
        	model.set("number", data[6]);
        	}
        else
        	{
        	model.set("number", 0);
        	retval = false;
        	}
        
        for(int i = 0; i < 255; i++)
            {
            setParameterByIndex(i, data[i + 7]);
            }
        revise();  
        return retval;     
        }

        
    public void merge(Model otherModel, double probability)
        {
        String[] keys = getModel().getKeys();
        for(int i = 0; i < keys.length; i++)
            {
            if (keys[i].equals("id")) continue;
            if (keys[i].equals("number")) continue;
            if (keys[i].equals("name")) continue;
                
            if (coinToss(probability))
                {
                if (otherModel.isString(keys[i]))
                    {
                    getModel().set(keys[i], otherModel.get(keys[i], getModel().get(keys[i], "")));
                    }
                else
                    {
                    getModel().set(keys[i], otherModel.get(keys[i], getModel().get(keys[i], 0)));
                    }
                }
            }
        }
    
    public void immutableMutate(String key)
        {
        /*
        // we randomize these specially, taking care not to do the high waves
        if (key.equals("osc1shape") || key.equals("osc2shape"))
            {
            if (coinToss(0.5))
                model.set(key, 0);
            else
                model.set(key, random.nextInt(WAVES_LONG.length -1) + 1);
            }
        */
        }
        

    public boolean requestCloseWindow() { return true; }

    public String getSynthName() { return "Microwave II/XT/XTk [Multi]"; }
    
    public String getPatchName() { return model.get("name", "Init Sound V1.1 "); }
    

                
    }