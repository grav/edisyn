package edisyn.synth.behringerubxa;

import edisyn.Midi;
import edisyn.Synth;
import edisyn.gui.*;

import javax.swing.*;
import java.awt.*;

public class BehringerUBXa extends Synth {

    private static final int NUM_PARAMS_DIALS = 5;
    public static final String BITMASK_SEP = "@@";

    private final Object[] dials = {
            "ControlPortamentoAmount", 0, 0, 16383, false,
            "ControlPortamentoBend", 1, 0, 16383, false,
            "ControlDetune", 2, 0, 16383, true,
            "ControlVoiceDetune", 3, 0, 16383, false,
            "ControlUnison", 4, 0, 1, false,
            "ControlPolyphonicVoiceCount", 5, 1, 8, false,
            "ControlUnisonVoiceCount", 6, 1, 16, false,
            "ControlPortamentoSpread", 11, 0, 255, false,
            "ControlPortamentoRange", 12, 0, 255, false,
            "PerformanceVolume", 128, 0, 16383, false,
            "PerformancePitchBendSensitivity", 130, 0, 16383, false,
            "PerformanceTranspose", 131, 0, 16383, true,
            "PerformanceLFORate", 132, 0, 16383, false,
            "PerformanceLFOChannelAmount", 134, 0, 16383, false,
            "PerformanceMpePitchLowerSensitivity", 136, 0, 96, false,
            "PerformanceMpePitchUpperSensitivity", 137, 0, 96, false,
            "PerformanceLFOTrim", 138, 0, 255, false,
            "ModulationLFOTrigPoint", 256, 0, 16383, false,
            "ModulationLFORate", 257, 0, 16383, false,
            "ModulationLFOPhase", 258, 0, 16383, false,
            "ModulationChannel1Amount", 259, 0, 16383, false,
            "ModulationChannel2Amount", 260, 0, 16383, false,
            "ModulationLFOTrim", 267, 0, 255, false,
            "EnvelopesFilterA", 384, 0, 16383, false,
            "EnvelopesFilterD", 385, 0, 16383, false,
            "EnvelopesFilterS", 386, 0, 16383, false,
            "EnvelopesFilterR", 387, 0, 16383, false,
            "EnvelopesLoudnessA", 388, 0, 16383, false,
            "EnvelopesLoudnessD", 389, 0, 16383, false,
            "EnvelopesLoudnessS", 390, 0, 16383, false,
            "EnvelopesLoudnessR", 391, 0, 16383, false,
            "EnvelopesModChannel1A", 392, 0, 16383, false,
            "EnvelopesModChannel1Delay", 393, 0, 16383, false,
            "EnvelopesModChannel2A", 394, 0, 16383, false,
            "EnvelopesModChannel2Delay", 395, 0, 16383, false,
            "EnvelopesPedalR", 396, 0, 16383, false,
            "OscillatorsOSC1PWAmount", 512, 0, 16383, false,
            "OscillatorsOSC1Transpose", 513, 0, 16383, true,
            "OscillatorsOSC2PWAmount", 514, 0, 16383, false,
            "OscillatorsOSC2Transpose", 515, 0, 16383, true,
            "OscillatorsOSC1PWTrimL", 521, 0, 255, false,
            "OscillatorsOSC1PWTrimR", 522, 127, 255, false,
            "OscillatorsOSC2PWTrimL", 523, 0, 255, false,
            "OscillatorsOSC2PWTrimR", 524, 127, 255, false,
            "OscillatorsModDepth", 525, 6, 63, false,
            "OscillatorsPWMDepth", 526, 0, 255, false,
            "OscillatorsPWMOffsetShift", 527, 0, 255, false,
            "OscillatorsChaos", 528, 0, 63, false,
            "OscillatorsVPOError", 529, 0, 31, false,
            "OscillatorsDriftSpeed", 530, 0, 63, false,
            "OscillatorsVPOErrorChaos", 531, 0, 63, false,
            "OscillatorsOscAutoInitF", 532, 0, 127, false,
            "OscillatorsFEnvRange", 533, 1, 63, false,
            "OscillatorsModDepth2", 534, 6, 63, false,
            "FilterFrequency", 640, 0, 16383, false,
            "FilterResonance", 641, 0, 16383, false,
            "FilterModulation", 642, 0, 16383, false,
            "FilterNoise", 643, 0, 16383, false,
            "FilterResonanceTrim", 645, 0, 255, false,
            "FilterResonanceTrim4Pole", 646, 0, 255, false,
            "FilterFrequencyRange", 647, 0, 127, false,
            "FilterZero2pOffset", 648, 0, 48, false,
            "FilterZero4pOffset", 649, 0, 48, false,
            "FilterModulationRange", 650, 0, 127, false,

    };

    private static final int NUM_PARAMS_CHECKBOXES = 4;

    private final Object[] checkboxGroups = {
            "ModulationLFOMods",261,4,new String[]{"LFO Track on~LFO Track off","LFOEnv2 Rate on~LFOEnv2 Rate off","TempoLock","LFOTrig"},
    };

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


    public BehringerUBXa() {

        assert dials.length % NUM_PARAMS_DIALS == 0;


        JComponent soundPanel = new SynthPanel(this);
        addTab("Controls", soundPanel);
        JComponent vbox = new VBox();
        soundPanel.add(vbox, BorderLayout.CENTER);

        JComponent hbox = null;

        for (int i = 0; i < dials.length; i += NUM_PARAMS_DIALS) {
            if (i % (NUM_PARAMS_DIALS * 10) == 0){
                hbox = new HBox();
                vbox.add(hbox);
            }
            String label = (String) dials[i];
            String key = (String) dials[i];
            int minVal = (int) dials[i + 2];
            int maxVal = (int) dials[i + 3];
            boolean symmetric = (boolean) dials[i + 4];
            int sub = symmetric ? maxVal / 2 + 1 : 0;

            String[] lbls = splitAtCapitalLetter(label, 10);


            LabelledDial comp = new LabelledDial(lbls[0], this, key, Style.COLOR_A(), minVal, maxVal, sub) {
                public boolean isSymmetric() {
                    return symmetric;
                }
            };
            if (lbls.length > 1) {
                comp.addAdditionalLabel(lbls[1]);
            }
            hbox.add(comp);

        }

        assert checkboxGroups.length % NUM_PARAMS_CHECKBOXES == 0;

        for (int i = 0; i < checkboxGroups.length; i+=NUM_PARAMS_CHECKBOXES) {
            hbox = new HBox();
            vbox.add(hbox);
            String key = (String) checkboxGroups[i];

            int bitWidth = (int) checkboxGroups[i+2];
            String[] lbls = (String[]) checkboxGroups[i + 3];
            assert lbls.length == bitWidth;
            for (String lbl : lbls) {
                JComponent comp;
                if (lbl.contains("~")){
                    String [] strs = lbl.split("~");
                    String prefix = longestCommonWordPrefix(strs[0], strs[1]);
                    comp = new Chooser(prefix, this, key+BITMASK_SEP+lbl, strs, new int[]{0,1});
                } else {
                    comp = new CheckBox(lbl, this, key+ BITMASK_SEP +lbl);
                }
                hbox.add(comp);
            }
        }


    }

    @Override
    public Object[] emitAll(String key) {
        for (int i = 0; i < dials.length; i += NUM_PARAMS_DIALS) {
            String label = (String) dials[i];
            if (label.equals(key)) {
                int param = (int) dials[i + 1];
                int val = getModel().get(key);
                return buildNRPN(getChannelOut(),
                        param, val);
            }
        }

        String keyPrefix = key.split(BITMASK_SEP)[0];

        for (int i = 0; i < checkboxGroups.length; i += NUM_PARAMS_CHECKBOXES) {
            if (checkboxGroups[i].equals(keyPrefix)) {
                int param = (int) checkboxGroups[i+1];
                int sum = 0;
                int n = 0;
                for (String checkbox : (String[]) checkboxGroups[i + 3]) {
                    String k = (keyPrefix + BITMASK_SEP + checkbox);
                    sum += getModel().get(k) << n;
                    n++;
                }
                return buildNRPN(getChannelOut(),param,sum);
            }
        }

        assert false;
        return null;
    }

    @Override
    public void handleSynthCCOrNRPN(Midi.CCData data) {
        for (int i = 0; i < dials.length; i += NUM_PARAMS_DIALS) {
            if (dials[i+1].equals(data.number)) {
                String label = (String) dials[i];
                getModel().set(label, data.value);
                break;
            }
        }
    }
}