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



    private final java.util.List<String> usedKeys = new ArrayList<>();


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


    private void addDialByKey(JComponent container, String key, String label) {
        assert !usedKeys.contains(key);
        for (int i = 0; i < dials.length; i += NUM_PARAMS_DIALS) {
            if (dials[i].equals(key)) {
                int minVal = (int) dials[i + 2];
                int maxVal = (int) dials[i + 3];
                boolean symmetric = (boolean) dials[i + 4];

                addDial(container, key, label, minVal, maxVal, symmetric);
                break;
            }
        }


    }

    private JComponent makeEnv(String title, String a, String d, String s, String r) {
        VBox container = new VBox();
        JComponent c = new Category(this, title, Color.WHITE);
        container.add(c);
        HBox envDials = new HBox();
        addDialByKey(envDials, a, "Attack");
        addDialByKey(envDials, d, "Decay");
        addDialByKey(envDials, s, "Sustain");
        addDialByKey(envDials, r, "Release");

        EnvelopeDisplay ed = new EnvelopeDisplay(
                this, Style.ENVELOPE_COLOR(),
                new String[]{null, a, d, null, r},
                new String[]{null, null, s, s, null},
                new double[]{0, 0.25 / 16383, 0.25 / 16383, 0.25, 0.25 / 16383},
                new double[]{0, 1.0, 1.0 / 16383, 1.0 / 16383, 0}

        );
        envDials.add(ed);
        container.add(envDials);

        return container;
    }

    public BehringerUBXa() {
        assert checkboxGroups.length % NUM_PARAMS_CHECKBOXES == 0;
        assert dials.length % NUM_PARAMS_DIALS == 0;
        assert selectors.length % NUM_PARAMS_SELECTORS == 0;

        JComponent main = new SynthPanel(this);
        JComponent vbox = new VBox();

        Category c = new Category(this,"Modulation",Color.WHITE);
        vbox.add(c);
        HBox modDials = new HBox();
        vbox.add(modDials);
        addDialByKey(modDials,"ModulationLFOTrigPoint","LFO Trig Point");
        addDialByKey(modDials,"ModulationLFORate","LFO Rate");
        addDialByKey(modDials,"ModulationLFOPhase","LFO Phase");
        addDialByKey(modDials,"ModulationChannel1Amount","Channel 1 Amount");
        addDialByKey(modDials,"ModulationChannel2Amount","Channel 2 Amount");
        addDialByKey(modDials,"ModulationLFOTrim","LFO Trim");
        HBox modSelsC1 = new HBox();
        vbox.add(modSelsC1);
        addCheckboxGroupByKey(modSelsC1,"ModulationChannel1Sends");
        addCheckboxGroupByKey(modSelsC1,"ModulationChannel1Mods");
        HBox modSelsC2 = new HBox();
        vbox.add(modSelsC2);
        addCheckboxGroupByKey(modSelsC2,"ModulationChannel2Sends");
        addCheckboxGroupByKey(modSelsC2,"ModulationChannel2Mods");

        addSelectorByKey(vbox,"ModulationLFOShapes","LFO Shapes");

        c = new Category(this, "Oscillators", Color.WHITE);
        vbox.add(c);

        HBox oscDials = new HBox();
        addDialByKey(oscDials, "OscillatorsOSC1Transpose", "OSC1 Transpose");
        addDialByKey(oscDials, "OscillatorsOSC1PWAmount", "OSC1 PW Amount");
        addDialByKey(oscDials, "OscillatorsOSC2Transpose", "OSC2 Transpose");
        addDialByKey(oscDials, "OscillatorsOSC2PWAmount", "OSC2 PW Amount");
        vbox.add(oscDials);

        HBox oscButtons = new HBox();
        addSelectorByKey(oscButtons, "OscillatorsOSC1Shapes", "OSC1 Shapes");
        addCheckboxGroupByKey(oscButtons, "OscillatorsMode");
        addSelectorByKey(oscButtons, "OscillatorsOSC2Shapes", "OSC2 Shapes");
        vbox.add(oscButtons);

        HBox oscillatorEnableButtons = new HBox();
        addCheckboxGroupByKey(oscillatorEnableButtons, "OscillatorsOSC1State");
        addSelectorByKey(oscillatorEnableButtons, "OscillatorsOSC2State", "OSC2 State");
        vbox.add(oscillatorEnableButtons);

        c = new Category(this, "Filter", Color.WHITE);
        vbox.add(c);

        HBox filterDials = new HBox();
        addDialByKey(filterDials, "FilterFrequency", "Frequency");
        addDialByKey(filterDials, "FilterResonance", "Resonance");
        addDialByKey(filterDials, "FilterModulation", "Modulation");
        addDialByKey(filterDials, "FilterNoise", "Noise");
        vbox.add(filterDials);

        HBox filterButtons = new HBox();
        addCheckboxGroupByKey(filterButtons, "FilterModes");

        vbox.add(filterButtons);

        JComponent filterEnv = makeEnv("Filter Envelope", "EnvelopesFilterA", "EnvelopesFilterD", "EnvelopesFilterS", "EnvelopesFilterR");
        vbox.add(filterEnv);

        JComponent loudnessEnv = makeEnv("Loudness Envelope", "EnvelopesLoudnessA", "EnvelopesLoudnessD", "EnvelopesLoudnessS", "EnvelopesLoudnessR");
        vbox.add(loudnessEnv);


        main.add(vbox, BorderLayout.CENTER);
        addTab("Main", main);

        JComponent generalPanel = new SynthPanel(this);

        vbox = new VBox();
        generalPanel.add(vbox, BorderLayout.CENTER);


        addDials(vbox);

        // Check that we've added all dials at this point
        for (int i = 0; i < dials.length; i += NUM_PARAMS_DIALS) {
            String key = (String) dials[i];
            assert (usedKeys.contains(key));

        }

        addCheckboxGroups(vbox);

        addSelectors(vbox);
        addTab("General", generalPanel);

    }

    private void addSelectorByKey(JComponent container, String key, String label) {
        boolean found = false;
        for (int i = 0; i < selectors.length; i += NUM_PARAMS_SELECTORS) {
            if (key.equals(selectors[i])) {
                String[] opts = (String[]) selectors[i + 4];
                addSelector(container, key, label, opts);
                usedKeys.add(key);
                found = true;
            }
        }
        assert found;
    }

    private void addCheckboxGroupByKey(JComponent container, String key) {
        for (int i = 0; i < checkboxGroups.length; i += NUM_PARAMS_CHECKBOXES) {
            if (key.equals(checkboxGroups[i])) {
                String[] labels = (String[]) checkboxGroups[i + 3];
                addCheckboxGroup(container, key, labels);
                usedKeys.add(key);
                break;
            }
        }
    }

    private void addSelector(JComponent container, String key, String label, String[] opts) {
        int[] vals = new int[opts.length];
        for (int j = 0; j < opts.length; j++) {
            vals[j] = j;
        }
        JComponent comp = new Chooser(label, this, key, opts, vals);

        container.add(comp);
    }

    private void addSelectors(JComponent container) {

        JComponent hbox = null;
        for (int i = 0; i < selectors.length; i += NUM_PARAMS_SELECTORS) {
            String key = (String) selectors[i];
            if (usedKeys.contains(key)) continue;
            if (i % (NUM_PARAMS_SELECTORS * 4) == 0) {
                hbox = new HBox();
                container.add(hbox);
            }
            String[] opts = (String[]) selectors[i + 4];
            addSelector(hbox, key, key,opts);
        }
    }

    private void addDial(JComponent container, String key, String lbl, int minVal, int maxVal, boolean symmetric) {
        int sub = symmetric ? maxVal / 2 + 1 : 0;


        LabelledDial comp = new LabelledDial(lbl, this, key, Style.COLOR_A(), minVal, maxVal, sub) {
            public boolean isSymmetric() {
                return symmetric;
            }
        };

        container.add(comp);
        usedKeys.add(key);

    }

    private void addDials(JComponent container) {
        for (String dialGroup : dialGroups) {
            JComponent vbox = new VBox();
            Category c = new Category(this, dialGroup, Color.WHITE);
            vbox.add(c);
            JComponent hbox = null;

            int j = 0;
            for (int i = 0; i < dials.length; i += NUM_PARAMS_DIALS) {

                String key = (String) dials[i];
                if (key.indexOf(dialGroup) != 0) continue;
                if (usedKeys.contains(key)) continue;

                if (j % 10 == 0) {
                    hbox = new HBox();
                    vbox.add(hbox);
                }
                j += 1;
                int minVal = (int) dials[i + 2];
                int maxVal = (int) dials[i + 3];
                boolean symmetric = (boolean) dials[i + 4];

                String label = key.substring(dialGroup.length());

                addDial(hbox, key, label, minVal, maxVal, symmetric);


            }
            container.add(vbox);
        }


    }

    private void addCheckboxGroup(JComponent container, String key, String[] lbls) {
        for (String lbl : lbls) {
            JComponent comp;
            if (lbl.contains("~")) {
                String[] strs = lbl.split("~");
                String prefix = longestCommonWordPrefix(strs[0], strs[1]);
                String[] opts = new String[]{strs[1], strs[0]}; // order is "switched"
                comp = new Chooser(prefix, this, key + BITMASK_SEP + lbl, opts, new int[]{0, 1});
            } else {
                comp = new CheckBox(lbl, this, key + BITMASK_SEP + lbl);
            }
            container.add(comp);
        }

    }

    private void addCheckboxGroups(JComponent container) {

        for (int i = 0; i < checkboxGroups.length; i += NUM_PARAMS_CHECKBOXES) {
            String key = (String) checkboxGroups[i];
            if (usedKeys.contains(key)) continue;
            JComponent hbox = new HBox();
            Category c = new Category(this, key, Color.WHITE);
            c.add(hbox);
            container.add(c);
            String[] labels = (String[]) checkboxGroups[i + 3];
            addCheckboxGroup(hbox, key, labels);
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

        for (int i = 0; i < selectors.length; i += NUM_PARAMS_SELECTORS) {
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
            if (checkboxGroups[i + 1].equals(data.number)) {
                String prefix = (String) checkboxGroups[i];
                for (int j = 0; j < (int) checkboxGroups[i + 2]; j++) {
                    String suffix = ((String[]) checkboxGroups[i + 3])[j];
                    String key = prefix + BITMASK_SEP + suffix;
                    int val = (data.value & (1 << j)) >> j;
                    getModel().set(key, val);
                }
                return;
            }
        }

        for (int i = 0; i < selectors.length; i += NUM_PARAMS_SELECTORS) {
            if (selectors[i + 1].equals(data.number)) {
                String key = (String) selectors[i];
                getModel().set(key, data.value);
            }
        }
    }
}