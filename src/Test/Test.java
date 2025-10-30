package Test;

import java.awt.EventQueue;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;

import UI.Form_GiaoDienChinh;

public class Test {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new FlatDarkLaf());
					Form_GiaoDienChinh frame = new Form_GiaoDienChinh();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
