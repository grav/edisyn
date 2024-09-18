package edisyn.synth.behringerubxa;

import edisyn.Midi;
import edisyn.Synth;
import edisyn.gui.*;

import javax.swing.*;
import java.awt.*;

public class BehringerUBXa extends Synth {

    private static final int NUM_PARAMS_DIALS = 5;
    public static final String BITMASK_SEP = "@@";

    private String lastDialEmitKey = null;
    private int lastDialEmitIdx = -1;

    private int lastDialReceiveIdx = -1;


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
            "FilterEnvChaos", 652, 0, 31, false,
            "FilterLFORange", 653, 0, 127, false,
            "FilterChaos", 654, 0, 63, false,
            "FilterDriftSpeed", 655, 0, 63, false,
            "FilterTrackOffset", 656, 0, 63, false,
            "FilterPedalRange", 657, 0, 96, false,
            "FilterVPOError", 658, 0, 63, false,
            "FilterVPOErrorChaos", 659, 0, 63, false,
            "FilterInitFreq", 660, 0, 127, false,
            "FilterTrackAmount", 664, 0, 64, false,
            "FilterEnvAttackLinearity", 665, 0, 31, false,
            "FilterEnvDecayLinearity", 666, 0, 31, false,
            "ModMatrixBus1Amount", 768, 0, 16383, true,
            "ModMatrixBus2Amount", 769, 0, 16383, true,
            "ModMatrixBus3Amount", 770, 0, 16383, true,
            "ModMatrixBus4Amount", 771, 0, 16383, true,
            "ModMatrixBus5Amount", 772, 0, 16383, true,
            "ModMatrixBus6Amount", 773, 0, 16383, true,
            "ModMatrixBus7Amount", 774, 0, 16383, true,
            "ModMatrixBus8Amount", 775, 0, 16383, true,
            "AmplifierOffset2P", 2049, 0, 255, false,
            "AmplifierOffsetUni", 2050, 0, 255, false,
            "AmplifierOffsetMaster", 2051, 0, 255, false,
            "AmplifierEnvChaos", 2053, 0, 31, false,
            "AmplifierEnvAttackLinearity", 2054, 0, 31, false,
            "AmplifierEnvDecayLinearity", 2055, 0, 31, false,
            "AmplifierLFORange", 2056, 0, 127, false,
            "PanningVoice1", 2176, 0, 255, false,
            "PanningVoice2", 2177, 0, 255, false,
            "PanningVoice3", 2178, 0, 255, false,
            "PanningVoice4", 2179, 0, 255, false,
            "PanningVoice5", 2180, 0, 255, false,
            "PanningVoice6", 2181, 0, 255, false,
            "PanningVoice7", 2182, 0, 255, false,
            "PanningVoice8", 2183, 0, 255, false,
            "PanningVoice9", 2184, 0, 255, false,
            "PanningVoice10", 2185, 0, 255, false,
            "PanningVoice11", 2186, 0, 255, false,
            "PanningVoice12", 2187, 0, 255, false,
            "PanningVoice13", 2188, 0, 255, false,
            "PanningVoice14", 2189, 0, 255, false,
            "PanningVoice15", 2190, 0, 255, false,
            "PanningVoice16", 2191, 0, 255, false,
            "AtrophyProfileNameA", 2304, 32, 255, false,
            "AtrophyProfileNameB", 2305, 32, 255, false,
            "AtrophyProfileNameC", 2306, 32, 255, false,
            "AtrophyProfileNameD", 2307, 32, 255, false,
            "AtrophyProfileNameE", 2308, 32, 255, false,
            "AtrophyProfileNameF", 2309, 32, 255, false,
            "SequencerStepCount", 2432, 0, 128, false,
            "SequencerGatetime", 2434, 0, 99, false,


    };

    private static final int NUM_PARAMS_CHECKBOXES = 4;

    private final Object[] checkboxGroups = {
            "ControlPortamentoSettings",7,4,new String[]{"Match","Quantize","Bend","Exponential"},
            "PerformanceSettings",129,6,new String[]{"Bend Osc2 only~Bend Osc1 & Osc2","Custom bend~Bend +/- 2","OSC1","OSC2","Upper","Lower"},
            "PerformanceLFOMods",135,4,new String[]{"Track","Envelope","TempoLock","LFOTrig"},
            "ModulationChannel1Sends",263,3,new String[]{"C1 OSC1 LFO on~C1 OSC1 LFO off","C1 OSC2 LFO on~C1 OSC2 LFO off","C1 Flt LFO on~C1 Flt LFO off"},
            "ModulationChannel1Mods",264,2,new String[]{"C1 Quant on~C1 Quant off","LFOEnv1 Inv on~LFOEnv1 Inv off"},
            "ModulationChannel2Sends",265,3,new String[]{"C2 PWM1 LFO on~C2 PWM1 LFO off","C2 PWM2 LFO on~C2 PWM2 LFO off","C2 Vol LFO on~C2 Vol LFO off"},
            "ModulationChannel2Mods",266,2,new String[]{"C2 Quant on~C2 Quant off","LFOEnv2 Inv on~LFOEnv2 Inv off"},
            "EnvelopesFilterMods",397,5,new String[]{"Flip","Repeat","Retrig","Loop","Legato"},
            "EnvelopesLoudnessMods",398,5,new String[]{"Flip","Repeat","Retrig","Loop","Legato"},
            "OscillatorsMode",516,2,new String[]{"OSC2 sync on~OSC2 sync off","OSC2 f-env on~OSC2 f-env off"},
            "OscillatorsOSC1State",517,3,new String[]{"OSC1 full~OSC1 off","OSC1 VCO LFO 180~OSC1 VCO LFO 0","OSC1 PWM LFO 180~OSC1 PWM LFO 0"},
            "FilterModes",644,2,new String[]{"Filter track on~Filter track off","4 pole filter~2 pole filter"},
            "MidiSong",1156,5,new String[]{"Position In","Stop on Seek","Stop all off","Start En Seq","Start En Arp"},
            "MidiLocalControl",1161,8,new String[]{"Local Ctrl","Din Rt","Din Clock","USB Rt","USB Clock","ProgChngeRx","ProgChngeTx","NRPNTx"},
            "MidiForwarding",1162,8,new String[]{"Din to Usb","Usb to Din","Din to Din","Rt to Usb","Rt to Din","Clk to Din","Clk to Usb","ArpSeq Sel"},
            "MidiUSBControl",1163,4,new String[]{"USB NRPN TX","USB NRPN RX","USB CC TX","USB CC RX"},
            "MidiDinControl",1164,4,new String[]{"DIN NRPN TX","DIN NRPN RX","DIN CC TX","DIN CC RX"},
            "VoiceToggle1to8",1664,8,new String[]{"Voice 1","Voice 2","Voice 3","Voice 4","Voice 5","Voice 6","Voice 7","Voice 8"},
            "VoiceToggle9to16",1665,8,new String[]{"Voice 9","Voice 10","Voice 11","Voice 12","Voice 13","Voice 14","Voice 15","Voice 16"},
            "VoiceIgnorePWMCalibration1to8",1666,8,new String[]{"Voice 1","Voice 2","Voice 3","Voice 4","Voice 5","Voice 6","Voice 7","Voice 8"},
            "VoiceIgnorePWMCalibration9to16",1667,8,new String[]{"Voice 9","Voice 10","Voice 11","Voice 12","Voice 13","Voice 14","Voice 15","Voice 16"},
            "VoiceIgnoreVCOCalibration1to8",1668,8,new String[]{"Voice 1","Voice 2","Voice 3","Voice 4","Voice 5","Voice 6","Voice 7","Voice 8"},
            "VoiceIgnoreVCOCalibration9to16",1669,8,new String[]{"Voice 9","Voice 10","Voice 11","Voice 12","Voice 13","Voice 14","Voice 15","Voice 16"},
            "VoiceIgnoreVCFCalibration1to8",1670,8,new String[]{"Voice 1","Voice 2","Voice 3","Voice 4","Voice 5","Voice 6","Voice 7","Voice 8"},
            "VoiceIgnoreVCFCalibration9to16",1671,8,new String[]{"Voice 9","Voice 10","Voice 11","Voice 12","Voice 13","Voice 14","Voice 15","Voice 16"},
            "SurfaceLeverSettings",1794,3,new String[]{"Invert Left","Invert Right","Swap Levers"},
            "ProgramPerformancePanelOpts",2560,3,new String[]{"Save Patch","Load Transp","Transp MIDI"},

    };

    private static final int NUM_PARAMS_SELECTORS = 5;

    private final Object[] selectors = {
            "ControlUnisonNotePriority", 9, 0, 2, new String[]{"Below", "Above", "Last"},
            "ControlChordModeNotePriority", 10, 0, 2, new String[]{"Below", "Above", "Last"},
            "PerformanceLFOShapes", 133, 0, 7, new String[]{"Sine", "Saw", "Square", "InverseSaw", "SH", "Triangle", "Sample Hold", "Noise"},
            "ModulationLFOShapes", 262, 0, 7, new String[]{"Sine", "Saw", "Square", "InverseSaw", "SH", "Triangle", "Sample Hold", "Noise"},
            "OscillatorsOSC1Shapes", 518, 0, 2, new String[]{"Osc1 pulse", "Osc1 saw", "Osc1 tri"},
            "OscillatorsOSC2State", 519, 0, 2, new String[]{"Osc2 half", "Osc2 full", "Osc2 off"},
            "OscillatorsOSC2Shapes", 520, 0, 2, new String[]{"Osc2 pulse", "Osc2 saw", "Osc2 tri"},
            "OscillatorsOSC1Quantization", 535, 0, 5, new String[]{"Octaves", "Semitones", "Free", "+/- Octaves", "+/- Semitones", "+/- Free"},
            "OscillatorsOSC2Quantization", 536, 0, 5, new String[]{"Octaves", "Semitones", "Free", "+/- Octaves", "+/- Semitones", "+/- Free"},
            "ModMatrixBus1Source", 776, 0, 44, new String[]{"Empty", "ChannelPressure", "PolyAftertouch", "BreathControl", "ModChannel1Envelope", "ModChannel2Envelope", "ModChannel1", "ModChannel2", "Expression", "Cutoff", "FilterEnvelope", "KeyTrack", "KeyTrackPoly", "LoudnessEnvelope", "MainLFO", "SecondaryLFO", "Noise", "ModWheel", "Oscillator1", "Oscillator2", "PulseWidth1", "PulseWidth2", "PitchBendAmount", "Resonance", "VoiceSlewer", "PerformanceChannel", "PanningLeft", "PanningRight", "KeyVelocity", "SpreadFtn", "GeneralPurpose1", "GeneralPurpose2", "GeneralPurpose3", "GeneralPurpose4", "ModWheelUp", "ModWheelDown", "MPETimbre", "MPEPitch", "MPEPressure", "EightLEightR", "FlipFlop", "Spiral", "Osc1Drift", "Osc2Drift", "FilterDrift"},
            "ModMatrixBus1Destination", 777, 0, 33, new String[]{"Empty", "ButterInput", "EnvelopesFilterA", "EnvelopesFilterD", "FilterFrequency", "FilterModulation", "FilterNoise", "EnvelopesFilterR", "FilterResonance", "EnvelopesFilterS", "EnvelopesLoudnessA", "EnvelopesLoudnessD", "EnvelopesLoudnessR", "EnvelopesLoudnessS", "ModulationChannel1Amount", "ModulationChannel2Amount", "ModulationLFOPhase", "ModulationLFORate", "OscillatorsOSC1PWAmount", "OscillatorsOSC1Transpose", "ControlDetune", "OscillatorsOSC2PWAmount", "OscillatorsOSC2Transpose", "ControlPanning", "ControlPortamentoAmount", "PerformanceLFOChannelAmount", "PerformanceLFORate", "ManualMastertune", "ControlVoiceDetune", "PerformanceVolume", "CuttoffLfo", "Oscillator1Modulation", "Oscillator1Fenv", "Oscillator2Modulation", "Oscillator2Fenv"},
            "ModMatrixBus2Source", 780, 0, 44, new String[]{"Empty", "ChannelPressure", "PolyAftertouch", "BreathControl", "ModChannel1Envelope", "ModChannel2Envelope", "ModChannel1", "ModChannel2", "Expression", "Cutoff", "FilterEnvelope", "KeyTrack", "KeyTrackPoly", "LoudnessEnvelope", "MainLFO", "SecondaryLFO", "Noise", "ModWheel", "Oscillator1", "Oscillator2", "PulseWidth1", "PulseWidth2", "PitchBendAmount", "Resonance", "VoiceSlewer", "PerformanceChannel", "PanningLeft", "PanningRight", "KeyVelocity", "SpreadFtn", "GeneralPurpose1", "GeneralPurpose2", "GeneralPurpose3", "GeneralPurpose4", "ModWheelUp", "ModWheelDown", "MPETimbre", "MPEPitch", "MPEPressure", "EightLEightR", "FlipFlop", "Spiral", "Osc1Drift", "Osc2Drift", "FilterDrift"},
            "ModMatrixBus2Destination", 781, 0, 33, new String[]{"Empty", "ButterInput", "EnvelopesFilterA", "EnvelopesFilterD", "FilterFrequency", "FilterModulation", "FilterNoise", "EnvelopesFilterR", "FilterResonance", "EnvelopesFilterS", "EnvelopesLoudnessA", "EnvelopesLoudnessD", "EnvelopesLoudnessR", "EnvelopesLoudnessS", "ModulationChannel1Amount", "ModulationChannel2Amount", "ModulationLFOPhase", "ModulationLFORate", "OscillatorsOSC1PWAmount", "OscillatorsOSC1Transpose", "ControlDetune", "OscillatorsOSC2PWAmount", "OscillatorsOSC2Transpose", "ControlPanning", "ControlPortamentoAmount", "PerformanceLFOChannelAmount", "PerformanceLFORate", "ManualMastertune", "ControlVoiceDetune", "PerformanceVolume", "CuttoffLfo", "Oscillator1Modulation", "Oscillator1Fenv", "Oscillator2Modulation", "Oscillator2Fenv"},
            "ArpeggiatorMode", 1025, 0, 11, new String[]{"Up", "Down", "Inclusive", "Exclusive", "Random", "Order", "Up x2", "Down x2", "Up x3", "Down x3", "Up x2/x3", "Down x2/x3"},
            "ArpeggiatorTime", 1027, 0, 6, new String[]{"1/4 notes", "1/8 notes", "1/16 notes", "1/32 notes", "1/4 triplets", "1/8 triplets", "1/16 triplets"},
            "MidiSync", 1155, 0, 2, new String[]{"Internal", "USB", "DIN"},
            "MidiChannelUpperRx", 1157, 0, 17, new String[]{"Channel 1", "Channel 2", "Channel 3", "Channel 4", "Channel 5", "Channel 6", "Channel 7", "Channel 8", "Channel 9", "Channel 10", "Channel 11", "Channel 12", "Channel 13", "Channel 14", "Channel 15", "Channel 16", "All", "None"},
            "MidiChannelLowerRx", 1158, 0, 17, new String[]{"Channel 1", "Channel 2", "Channel 3", "Channel 4", "Channel 5", "Channel 6", "Channel 7", "Channel 8", "Channel 9", "Channel 10", "Channel 11", "Channel 12", "Channel 13", "Channel 14", "Channel 15", "Channel 16", "All", "None"},
            "MidiChannelUpperTX", 1159, 0, 16, new String[]{"Channel 1", "Channel 2", "Channel 3", "Channel 4", "Channel 5", "Channel 6", "Channel 7", "Channel 8", "Channel 9", "Channel 10", "Channel 11", "Channel 12", "Channel 13", "Channel 14", "Channel 15", "Channel 16", "RxChannel"},
            "MidiChannelLowerTX", 1160, 0, 16, new String[]{"Channel 1", "Channel 2", "Channel 3", "Channel 4", "Channel 5", "Channel 6", "Channel 7", "Channel 8", "Channel 9", "Channel 10", "Channel 11", "Channel 12", "Channel 13", "Channel 14", "Channel 15", "Channel 16", "RxChannel"},
            "MidiMpeProfile", 1165, 0, 2, new String[]{"Disabled", "Single", "Zones"},
            "KeyboardVelocityCurviness", 1281, 0, 2, new String[]{"Soft", "Medium", "Hard"},
            "KeyboardAftertouchAssignment", 1283, 0, 1, new String[]{"ChannelPressure", "PolyAftertouch"},
            "KeyboardAftertouchCurviness", 1284, 0, 2, new String[]{"Soft", "Medium", "Hard"},
            "PedalSustainAssignment", 1408, 0, 6, new String[]{"Patch Up", "Patch Down", "Program Up", "Program Down", "Sustain", "Hold", "Sostenuto"},
            "PedalSustainPolarity", 1409, 0, 2, new String[]{"Negative", "Positive", "Disabled"},
            "PedalProgramAssignment", 1411, 0, 6, new String[]{"Patch Up", "Patch Down", "Program Up", "Program Down", "Sustain", "Hold", "Sostenuto"},
            "PedalProgramPolarity", 1412, 0, 2, new String[]{"Negative", "Positive", "Disabled"},
            "PedalVibratoAssignment", 1417, 0, 8, new String[]{"Filter", "Vibrato", "Attack", "Release", "Decay", "General1 cc16", "General2 cc17", "General3 cc18", "General4 cc19"},
            "PedalFilterAssignment", 1421, 0, 8, new String[]{"Filter", "Vibrato", "Attack", "Release", "Decay", "General1 cc16", "General2 cc17", "General3 cc18", "General4 cc19"},
            "SysexDeviceId", 1536, 0, 15, new String[]{"Channel 1", "Channel 2", "Channel 3", "Channel 4", "Channel 5", "Channel 6", "Channel 7", "Channel 8", "Channel 9", "Channel 10", "Channel 11", "Channel 12", "Channel 13", "Channel 14", "Channel 15", "Channel 16"},
            "SequencerTime", 2433, 0, 6, new String[]{"1/4 notes", "1/8 notes", "1/16 notes", "1/32 notes", "1/4 triplets", "1/8 triplets", "1/16 triplets"},
            "SequencerSync", 2435, 0, 1, new String[]{"Global", "Retrigger"}


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


        addDials(vbox);

        addCheckboxGroups(vbox);

        addSelectors(vbox);

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
            if (i% (NUM_PARAMS_SELECTORS * 4) == 0 ){
                hbox = new HBox();
                container.add(hbox);
            }
            String label = (String) selectors[i];
            String [] opts = (String[]) selectors[i+4];
            addSelector(hbox, label, opts);
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

        for (int i = 0; i < dials.length; i += NUM_PARAMS_DIALS) {

            if (i % (NUM_PARAMS_DIALS * 10) == 0) {
                hbox = new HBox();
                container.add(hbox);
            }
            String key = (String) dials[i];
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
            JComponent hbox = new HBox();
            String key = (String) checkboxGroups[i];
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