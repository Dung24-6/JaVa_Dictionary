package Voice;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class Speech {
    private static Voice[] voices;

    public  static void main ( String[]args)  {

        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");

        VoiceManager vm;
        vm = VoiceManager.getInstance();
        voices=vm.getVoices();

        Voice voice = VoiceManager.getInstance().getVoice("kevin16");
        voice.allocate();
        voice.speak("Hi,My name is Ha Van Quoc Dung");
        voice.deallocate();
        //bản trên để test

        //Hàm thêm vào on-click
        /*
        private void SpeechActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SpeechActionPerformed

            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
            vm = VoiceManager.getInstance();
            Voice voice = VoiceManager.getInstance().getVoice("kevin16");
            voice.allocate();
            voice.speak(jTextField1.getText());
            voice.deallocate();
            }

            // Gọi hàm
         */



    }

}
