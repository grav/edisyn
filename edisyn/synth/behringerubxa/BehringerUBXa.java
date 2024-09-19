package edisyn.synth.behringerubxa;

import edisyn.Midi;
import edisyn.Synth;
import edisyn.gui.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static edisyn.synth.behringerubxa.ParameterList.*;

public class BehringerUBXa extends Synth {

    public static final String BITMASK_SEP = "@@";

    private String lastDialEmitKey = null;
    private int lastDialEmitIdx = -1;

    private int lastDialReceiveIdx = -1;


    private java.util.List<String> usedKeys = new ArrayList<>();




    public static String getSynthName() {
        return "Behringer UB-Xa";
    }

    public static String[] splitAtCapitalLetter(String input, int k) {
        // Check if the input length is greater than k
        if (input.length() <= k) {
            // If not, return the input as a single string in an array
            return new String[]{input};
        }

        int midpoint = input.length() / 2;

        // Search backward from the midpoint for a capital letter
        for (int i = midpoint; i > 0; i--) {
            if (Character.isUpperCase(input.charAt(i))) {
                return new String[]{
                        input.substring(0, i),
                        input.substring(i)
                };
            }
        }

        // If no capital letter is found before midpoint, search forward
        for (int i = midpoint; i < input.length(); i++) {
            if (Character.isUpperCase(input.charAt(i))) {
                return new String[]{
                        input.substring(0, i),
                        input.substring(i)
                };
            }
        }

        // If no capital letter is found, return the entire string as a single string in the array
        return new String[]{input};
    }

    public static String longestCommonWordPrefix(String str1, String str2) {
        String[] words1 = str1.split(" ");
        String[] words2 = str2.split(" ");

        int minLength = Math.min(words1.length, words2.length);

        StringBuilder commonPrefix = new StringBuilder();

        for (int i = 0; i < minLength; i++) {
            if (!words1[i].equals(words2[i])) {
                break;
            }
            commonPrefix.append(words1[i]).append(" ");
        }

        // Remove the last extra space if any common prefix is found
        if (!commonPrefix.isEmpty()) {
            commonPrefix.setLength(commonPrefix.length() - 1);
        }

        return commonPrefix.toString();
    }


    private void addDialByKey(JComponent container, String key){
        assert !usedKeys.contains(key);
        for (int i=0;i<dials.length;i+=NUM_PARAMS_DIALS){
            if (dials[i].equals(key)){
                int minVal = (int) dials[i + 2];
                int maxVal = (int) dials[i + 3];
                boolean symmetric = (boolean) dials[i + 4];

                addDial(container, key, minVal, maxVal, symmetric);
                usedKeys.add(key);
                break;
            }
        }



    }

    public BehringerUBXa() {

        try {

            JComponent main = new SynthPanel(this);
            JComponent vbox = new VBox();

            Category c = new Category(this,"Oscillators",Color.WHITE);
            vbox.add(c);

            HBox oscDials = new HBox();
            addDialByKey(oscDials, "OscillatorsOSC1Transpose");
            addDialByKey(oscDials, "OscillatorsOSC1PWAmount");
            addDialByKey(oscDials, "OscillatorsOSC2Transpose");
            addDialByKey(oscDials, "OscillatorsOSC2PWAmount");
            vbox.add(oscDials);

            HBox oscButtons = new HBox();
            addSelectorByKey(oscButtons,"OscillatorsOSC1Shapes");
            addCheckboxGroupByKey(oscButtons, "OscillatorsMode");
            addSelectorByKey(oscButtons,"OscillatorsOSC2Shapes");
            vbox.add(oscButtons);

            HBox oscillatorEnableButtons = new HBox();
            addCheckboxGroupByKey(oscillatorEnableButtons,"OscillatorsOSC1State");
            addSelectorByKey(oscillatorEnableButtons,"OscillatorsOSC2State");
            vbox.add(oscillatorEnableButtons);

            c = new Category(this, "Filter", Color.WHITE);
            vbox.add(c);

            HBox filterDials = new HBox();
            addDialByKey(filterDials,"FilterFrequency");
            addDialByKey(filterDials,"FilterResonance");
            addDialByKey(filterDials,"FilterModulation");
            addDialByKey(filterDials,"FilterNoise");
            vbox.add(filterDials);

            HBox filterButtons = new HBox();
            addCheckboxGroupByKey(filterButtons,"FilterModes");

            vbox.add(filterButtons);

            c = new Category(this, "Filter Envelope", Color.WHITE);
            vbox.add(c);
            HBox filterEnvDials = new HBox();
            addDialByKey(filterEnvDials,"EnvelopesFilterA");
            addDialByKey(filterEnvDials,"EnvelopesFilterD");
            addDialByKey(filterEnvDials,"EnvelopesFilterS");
            addDialByKey(filterEnvDials,"EnvelopesFilterR");

            EnvelopeDisplay ed = new EnvelopeDisplay(
                    this,Style.ENVELOPE_COLOR(),
                    new String[]{null,"EnvelopesFilterA","EnvelopesFilterD",null,"EnvelopesFilterR"},
                    new String[]{null,null,"EnvelopesFilterS","EnvelopesFilterS",null},
                    new double[]{0,0.25/16383,0.25/16383,0.25,0.25/16383},
                    new double[]{0,1.0,1.0/16383,1.0/16383,0}

            );
            filterEnvDials.add(ed);
            vbox.add(filterEnvDials);

            c = new Category(this, "Loudness Envelope", Color.WHITE);
            vbox.add(c);
            HBox ampEnvDials = new HBox();
            addDialByKey(ampEnvDials,"EnvelopesLoudnessA");
            addDialByKey(ampEnvDials,"EnvelopesLoudnessD");
            addDialByKey(ampEnvDials,"EnvelopesLoudnessS");
            addDialByKey(ampEnvDials,"EnvelopesLoudnessR");

            ed = new EnvelopeDisplay(
                    this,Style.ENVELOPE_COLOR(),
                    new String[]{null,"EnvelopesLoudnessA","EnvelopesLoudnessD",null,"EnvelopesLoudnessR"},
                    new String[]{null,null,"EnvelopesLoudnessS","EnvelopesLoudnessS",null},
                    new double[]{0,0.25/16383,0.25/16383,0.25,0.25/16383},
                    new double[]{0,1.0,1.0/16383,1.0/16383,0}

            );
            ampEnvDials.add(ed);

            vbox.add(ampEnvDials);


            main.add(vbox, BorderLayout.CENTER);
            addTab("Main", main);

            JComponent generalPanel = new SynthPanel(this);

            vbox = new VBox();
            generalPanel.add(vbox, BorderLayout.CENTER);


            addDials(vbox);

            addCheckboxGroups(vbox);

            addSelectors(vbox);
            addTab("General", generalPanel);

        } catch (Exception e){
            throw new Error("BOOM!");
        }

    }

    private void addSelectorByKey(JComponent container, String key) {
        for(int i =0; i<selectors.length;i+=NUM_PARAMS_SELECTORS){
            if(key.equals(selectors[i])){
                String [] opts = (String[]) selectors[i+4];
                addSelector(container, key, opts);
                usedKeys.add(key);
                break;
            }
        }
    }

    private void addCheckboxGroupByKey(JComponent container, String key) {
        for (int i=0;i<checkboxGroups.length;i+=NUM_PARAMS_CHECKBOXES){
            if (key.equals(checkboxGroups[i])) {
                String[] labels = (String[]) checkboxGroups[i + 3];
                addCheckboxGroup(container, key,labels);
                usedKeys.add(key);
                break;
            }
        }
    }

    private void addSelector(JComponent container,String key, String[] opts){
        int[] vals = new int[opts.length];
        for (int j = 0; j < opts.length; j++) {
            vals[j] = j;
        }
        JComponent comp = new Chooser(key, this, key, opts, vals);

        container.add(comp);
    }

    private void addSelectors(JComponent container) {
        JComponent hbox = null;
        for (int i = 0; i< selectors.length; i+=NUM_PARAMS_SELECTORS) {
            String key = (String) selectors[i];
            if (usedKeys.contains(key)) continue;
            if (i% (NUM_PARAMS_SELECTORS * 4) == 0 ){
                hbox = new HBox();
                container.add(hbox);
            }
            String [] opts = (String[]) selectors[i+4];
            addSelector(hbox, key, opts);
        }
    }

    private void addDial(JComponent container,String key, int minVal, int maxVal, boolean symmetric) {
        int sub = symmetric ? maxVal / 2 + 1 : 0;

        String[] lbls = splitAtCapitalLetter(key, 10);


        LabelledDial comp = new LabelledDial(lbls[0], this, key, Style.COLOR_A(), minVal, maxVal, sub) {
            public boolean isSymmetric() {
                return symmetric;
            }
        };
        if (lbls.length > 1) {
            comp.addAdditionalLabel(lbls[1]);
        }
        container.add(comp);
    }

    private void addDials(JComponent container) {
        JComponent hbox = null;

        assert dials.length % NUM_PARAMS_DIALS == 0;
        for (int i = 0; i < dials.length; i += NUM_PARAMS_DIALS) {

            String key = (String) dials[i];
            if (usedKeys.contains(key)) continue;

            if (i % (NUM_PARAMS_DIALS * 10) == 0) {
                hbox = new HBox();
                container.add(hbox);
            }
            int minVal = (int) dials[i + 2];
            int maxVal = (int) dials[i + 3];
            boolean symmetric = (boolean) dials[i + 4];

            addDial(hbox, key, minVal, maxVal, symmetric);


        }
    }

    private void addCheckboxGroup(JComponent container, String key,String[] lbls) {
        for (String lbl : lbls) {
            JComponent comp;
            if (lbl.contains("~")) {
                String[] strs = lbl.split("~");
                String prefix = longestCommonWordPrefix(strs[0], strs[1]);
                String[] opts = new String[]{strs[1],strs[0]}; // order is "switched"
                comp = new Chooser(prefix, this, key + BITMASK_SEP + lbl, opts, new int[]{0, 1});
            } else {
                comp = new CheckBox(lbl, this, key + BITMASK_SEP + lbl);
            }
            container.add(comp);
        }

    }

    private void addCheckboxGroups(JComponent container) {
        assert checkboxGroups.length % NUM_PARAMS_CHECKBOXES == 0;

        for (int i = 0; i < checkboxGroups.length; i += NUM_PARAMS_CHECKBOXES) {
            String key = (String) checkboxGroups[i];
            if (usedKeys.contains(key)) continue;
            JComponent hbox = new HBox();
            Category c = new Category(this, key, Color.WHITE);
            c.add(hbox);
            container.add(c);
            String[] labels = (String[]) checkboxGroups[i + 3];
            addCheckboxGroup(hbox,key,labels);
        }
    }

    private Object[] emitDial(String key, int i) {
        int param = (int) dials[i + 1];
        int val = getModel().get(key);
        return buildNRPN(getChannelOut(),
                param, val);
    }


    private Object[] emitSelector(String key, int i) {
        int param = (int) selectors[i + 1];
        int val = getModel().get(key);
        return buildNRPN(getChannelOut(),
                param, val);
    }

    private Object[] emitCheckboxGroup(String keyPrefix, int i) {
        int param = (int) checkboxGroups[i + 1];
        int sum = 0;
        int n = 0;
        for (String checkbox : (String[]) checkboxGroups[i + 3]) {
            String k = (keyPrefix + BITMASK_SEP + checkbox);
            sum += getModel().get(k) << n;
            n++;
        }
        return buildNRPN(getChannelOut(), param, sum);
    }

    @Override
    public Object[] emitAll(String key) {
        if (key.equals(lastDialEmitKey)) {
            // Caching
            return emitDial(key, lastDialEmitIdx);
        }

        for (int i = 0; i < dials.length; i += NUM_PARAMS_DIALS) {
            String label = (String) dials[i];
            if (label.equals(key)) {
                lastDialEmitKey = key;
                lastDialEmitIdx = i;
                return emitDial(key, i);
            }
        }

        String keyPrefix = key.split(BITMASK_SEP)[0];

        for (int i = 0; i < checkboxGroups.length; i += NUM_PARAMS_CHECKBOXES) {
            if (checkboxGroups[i].equals(keyPrefix)) {
                return emitCheckboxGroup(keyPrefix, i);
            }
        }

        for (int i= 0; i<selectors.length; i+=NUM_PARAMS_SELECTORS){
            if (selectors[i].equals(keyPrefix)) {
                return emitSelector(keyPrefix, i);
            }
        }

        assert false;
        return null;
    }

    private void setValueForDial(int dialIdx, int value) {
        String label = (String) dials[dialIdx];
        getModel().set(label, value);

    }

    private static String toBinaryString(int number) {
        // Convert the integer to a binary string
        String binaryString = Integer.toBinaryString(number);
        // Add the 0b prefix
        return "0b" + binaryString;
    }

    @Override
    public boolean getRequiresNRPNLSB() {
        return true;
    }

    @Override
    public boolean getRequiresNRPNMSB() {
        return true;
    }

    @Override
    public void handleSynthCCOrNRPN(Midi.CCData data) {

        if (dials[lastDialReceiveIdx + 1].equals(data.number)) {
            // Caching
            setValueForDial(lastDialReceiveIdx, data.value);
            return;
        }

        for (int i = 0; i < dials.length; i += NUM_PARAMS_DIALS) {
            if (dials[i + 1].equals(data.number)) {
                lastDialReceiveIdx = i;
                setValueForDial(i, data.value);
                return;
            }


        }


        for (int i = 0; i < checkboxGroups.length; i += NUM_PARAMS_CHECKBOXES) {
            if (checkboxGroups[i+1].equals(data.number)) {
                String prefix = (String) checkboxGroups[i];
                for (int j = 0; j < (int)checkboxGroups[i+2]; j++){
                    String suffix = ((String[]) checkboxGroups[i+3])[j];
                    String key = prefix + BITMASK_SEP + suffix;
                    int val = (data.value & (1 << j)) >> j;
                    getModel().set(key, val);
                }
                return;
            }
        }

        for (int i = 0; i < selectors.length; i += NUM_PARAMS_SELECTORS) {
            if (selectors[i+1].equals(data.number)) {
                String key = (String) selectors[i];
                getModel().set(key,data.value);
            }
        }
    }
}