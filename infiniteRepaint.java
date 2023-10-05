import javax.swing.JOptionPane;

public class infiniteRepaint implements Runnable{
    private PlayFrame playFrame;
    public infiniteRepaint(PlayFrame playFrame)
    {
        this.playFrame=playFrame;
    }


    @Override
    public void run()
    {
        while(true)
        {

            if(this.playFrame.getGameFlow().getOneMoreGame()==true&&this.playFrame.getGameFlow().getPlayerWin()==1)
            {
                int opt = JOptionPane.showConfirmDialog(null, "You win! One more game?", "Congratulations",JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                           
                if (opt == JOptionPane.OK_OPTION) {
                        
                    this.playFrame.dispose();
                    new StartFrame();
                }
                else if(opt==JOptionPane.NO_OPTION){
                    System.exit(0);
                }
                
                return;
            }
            else if(this.playFrame.getGameFlow().getOneMoreGame()==true&&this.playFrame.getGameFlow().getPlayerWin()==2)
            {
                int opt = JOptionPane.showConfirmDialog(null, "You lose Q~Q One more game?", "Nice try!", JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
                   
                if (opt == JOptionPane.OK_OPTION) {
                        
                    this.playFrame.dispose();
                    new StartFrame();
                }
                else if(opt==JOptionPane.NO_OPTION){
                    System.exit(0);
                }

                return;
            }



            this.playFrame.setGoldLabelTest("  X  "+this.playFrame.getGameFlow().getCoinCount());
            this.playFrame.changeCard(playFrame.getCard(), playFrame.getlocation());
            this.playFrame.setlocation(playFrame.getlocation());
            this.playFrame.repaint();
            doNothing(300);
        }
    }

    private static void doNothing(int milliseconds) {

        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
        }

}
}
