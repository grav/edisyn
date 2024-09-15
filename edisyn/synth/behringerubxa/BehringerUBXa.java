package edisyn.synth.behringerubxa;

import edisyn.gui.*;

import javax.swing.*;
import java.awt.*;

public class BehringerUBXa extends edisyn.Synth {

    private static final int NUM_PARAMS = 5;

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

    public BehringerUBXa() {

        assert dials.length % NUM_PARAMS == 0;


        JComponent soundPanel = new SynthPanel(this);
        addTab("Controls", soundPanel);
        JComponent container = new HBox();

        for (int i = 0; i < dials.length; i += NUM_PARAMS) {
            String label = (String) dials[i];
            String key = (String) dials[i];
            int minVal = (int) dials[i + 2];
            int maxVal = (int) dials[i + 3];
            boolean symmetric = (boolean) dials[i+4];
            int sub =  symmetric ? maxVal/2 + 1 : 0;

            String [] lbls = splitAtCapitalLetter(label,10);


            LabelledDial comp = new LabelledDial(lbls[0], this, key, Style.COLOR_A(), minVal, maxVal,sub) {
                public boolean isSymmetric() {
                    return symmetric ;
                }
            };
            if (lbls.length>1){
                comp.addAdditionalLabel(lbls[1]);
            }
            container.add(comp);

        }
        soundPanel.add(container, BorderLayout.CENTER);
    }

    public Object[] emitAll(String key) {
        int param = 0;
        for (int i = 0; i < dials.length; i += NUM_PARAMS) {
            String label = (String) dials[i];
            if (label.equals(key)) {
                param = (int) dials[i + 1];
                break;
            }
        }


        int val = getModel().get(key);
        return buildNRPN(getChannelOut(),
                param, val);
    }
}