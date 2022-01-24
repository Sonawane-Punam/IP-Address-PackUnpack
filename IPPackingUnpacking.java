import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.util.*;
class OwnListener extends WindowAdapter
{
    public void windowClosing(WindowEvent e)
    {
        System.exit(0);
    }
}
class IPPack
{
   public int Packing(int no1,int no2,int no3,int no4)
   {
        return ((no1<<24) | (no2<<16) | (no3<<8) | (no4));
   }

}

class IPUnpack
{
    public int[] Unpacking(int no)
    {
        int arr[] = new int[4];
        int no1,no2,no3,no4;
        no1=no & 0x000000FF;
        no2=no >> 8;
        no2=no2 & 0x000000FF;
        no3=no >> 16;
        no3=no3 & 0x000000FF;
        no4=no >> 24;

        arr[0] = no1;
        arr[1] = no2;
        arr[2] = no3;
        arr[3] = no4;
        return arr;
    }

}

/*
////////////////////////////////////////////////////////////////////////////////////////////////////

                                        PACKING FRAME

////////////////////////////////////////////////////////////////////////////////////////////////////
*/

class Window1
{
    public Frame fobj;
    public Window1()
    {
        Frame fobj = new Frame("Packing Upacking");
        Button bobj1 = new Button("Packing");
        Button bobjNext = new Button("Continue to Unpack");

        Label no1 = new Label("No1");
        Label no2 = new Label("No2");
        Label no3 = new Label("No3");
        Label no4 = new Label("No4");
        Label PackedNo = new Label("PackedNo");

        TextField no1t = new TextField();
        TextField no2t = new TextField();
        TextField no3t = new TextField();
        TextField no4t = new TextField();
        TextField PackedNot = new TextField();


        no1.setBounds(50,80,30,30);
        no1t.setBounds(90,80,80,30);

        no2.setBounds(190,80,30,30);
        no2t.setBounds(240,80,80,30);

        no3.setBounds(50,130,30,30);
        no3t.setBounds(90,130,80,30);

        no4.setBounds(190,130,30,30);
        no4t.setBounds(240,130,80,30);

        PackedNo.setBounds(30,180,70,30);
        PackedNot.setBounds(110,180,210,30);

        bobj1.setBounds(40,230,120,40);
        bobjNext.setBounds(175,230,150,40);

        fobj.add(bobj1);
        fobj.add(bobjNext);
        fobj.add(no1);
        fobj.add(no2);
        fobj.add(no3);
        fobj.add(no4);
        fobj.add(PackedNo);
        fobj.add(no1t);
        fobj.add(no2t);
        fobj.add(no3t);
        fobj.add(no4t);
        fobj.add(PackedNot);

        bobj1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                int no1 = Integer.parseInt(no1t.getText());
                int no2 = Integer.parseInt(no2t.getText());
                int no3 = Integer.parseInt(no3t.getText());
                int no4 = Integer.parseInt(no4t.getText());
                
                IPPack obj = new IPPack();

                int ret=obj.Packing(no1,no2,no3,no4);
                PackedNot.setText(String.valueOf(ret));
            }
        });
        bobjNext.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e) 
                {
                    int ino = Integer.parseInt((PackedNot.getText()).trim());
                    new Window2(ino);
                    fobj.dispose(); 
                };
            }
        );
        
        fobj.setSize(400,300);
        fobj.setLayout(null);
        fobj.setVisible(true);
        fobj.addWindowListener(new OwnListener());
    }
}


/*
////////////////////////////////////////////////////////////////////////////////////////////////////

                                        UNPACKING FRAME

////////////////////////////////////////////////////////////////////////////////////////////////////
*/

class Window2
{
    public Frame fobj1;
    public Window2(int packedNumber)
    {
        Frame fobj1 = new Frame("Packing Upacking");
        Button bobj2 = new Button("Unpacking");
        Button bobjback = new Button("Back");

        Label no1 = new Label("No1");
        Label no2 = new Label("No2");
        Label no3 = new Label("No3");
        Label no4 = new Label("No4");
        Label UnPackedNo = new Label("UnPackedNo");

        TextField no1t = new TextField();
        TextField no2t = new TextField();
        TextField no3t = new TextField();
        TextField no4t = new TextField();
        TextField UnPackedNot = new TextField();


        no1.setBounds(50,110,30,30);
        no1t.setBounds(90,110,80,30);

        no2.setBounds(190,110,30,30);
        no2t.setBounds(240,110,80,30);

        no3.setBounds(50,160,30,30);
        no3t.setBounds(90,160,80,30);

        no4.setBounds(190,160,30,30);
        no4t.setBounds(240,160,80,30);

        UnPackedNo.setBounds(40,50,90,30);
        UnPackedNot.setBounds(140,50,100,30);

        String str = (" "+packedNumber).trim();
        UnPackedNot.setText(str);

        bobj2.setBounds(70,230,120,40);
        bobjback.setBounds(210,230,120,40);

        fobj1.add(bobj2);
        fobj1.add(bobjback);
        fobj1.add(no1);
        fobj1.add(no2);
        fobj1.add(no3);
        fobj1.add(no4);
        //fobj.add(PackedNo);
        fobj1.add(UnPackedNo);
        fobj1.add(no1t);
        fobj1.add(no2t);
        fobj1.add(no3t);
        fobj1.add(no4t);
        fobj1.add(UnPackedNot);

        fobj1.setSize(400,300);
        fobj1.setLayout(null);
        fobj1.setVisible(true);

        

        bobj2.addActionListener
        (
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    
                    IPUnpack obj = new IPUnpack();
                    int arr[] = obj.Unpacking(packedNumber);

                    String no1 = (" "+arr[0]).trim();
                    String no2 = (" "+arr[1]).trim();
                    String no3 = (" "+arr[2]).trim();
                    String no4 = (" "+arr[3]).trim();

                    no1t.setText(no1);
                    no2t.setText(no2);
                    no3t.setText(no3);
                    no4t.setText(no4);
                }
            }
        );
        bobjback.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e) 
                {
                    new Window1();
                    fobj1.dispose(); 
                };
            }
        );

        fobj1.addWindowListener(new OwnListener());

    
    }
}

/*
////////////////////////////////////////////////////////////////////////////////////////////////////

                                        MAIN

////////////////////////////////////////////////////////////////////////////////////////////////////
*/
class IPPackingUnpacking
{
    public static void main(String arg[])
    {
        Window1 obj = new Window1();
        
        
    }
}