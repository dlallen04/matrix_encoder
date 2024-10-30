
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JOptionPane;

public class MatrixEncoder extends JFrame implements ActionListener{

	// Matrix Encoder Attributes
	private char[] characterArr = {
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
			'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
			'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			' ', '!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/',
			':', ';', '<', '=', '>', '?', '@',
			'[', '\\', ']', '^', '_', '`',
			'{', '|', '}', '~'};
	private int[] numberArr = {
			1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
			17, 18, 19, 20, 21, 22, 23, 24, 25, 26,
			27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42,
			43, 44, 45, 46, 47, 48, 49, 50, 51, 52,
			53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 
			63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78,
			79, 80, 81, 82, 83, 84, 85,
			86, 87, 88, 89, 90, 91,
			92, 93, 94, 95};
	private double[][] keyMatrix;
	private double[][] inverseKeyMatrix;
	private String mainText;
	private String message;
	private int r, c, i, j;
	
	// Matrix Encoder Component
	private JTextField[] keyMatrixBox;
	private JTextArea textArea; 
	private JScrollPane scrollPane;
	private JButton encodeButton;
	private JButton decodeButton;
	private JLabel matrixKeyLabel;
	
	
	MatrixEncoder() {
		
		// Set Frame Title
		setTitle("Matrix Encoder");
		
		
		// Set Variables
		keyMatrix = new double[2][2];
		inverseKeyMatrix = new double[2][2];
		mainText = "";
		
		
		// Set Components
		textArea = new JTextArea(mainText);
		scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(400, 300));
		textArea.setEditable(true);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		encodeButton = new JButton("Encode");
		encodeButton.setMargin(new Insets(2, 13, 2, 13));
		encodeButton.addActionListener(this);
		
		decodeButton = new JButton("Decode");
		decodeButton.setMargin(new Insets(2, 13, 2, 13));
		decodeButton.addActionListener(this);
		
		matrixKeyLabel = new JLabel("Key");
		matrixKeyLabel.setHorizontalAlignment(JLabel.CENTER);
		
		keyMatrixBox = new JTextField[4];
		
		for(int i = 0; i < 4; i++) {
			keyMatrixBox[i] = new JTextField();
			keyMatrixBox[i].setColumns(3);
		}
		
		
		// Setup Layout
		setLayout(new GridBagLayout());
		GridBagConstraints layoutConst = new GridBagConstraints();
		
		
		// Matrix Panel
		JPanel matrixPanel = new JPanel();
		matrixPanel.setLayout(new GridBagLayout());
		
		layoutConst.insets = new Insets(0, 0, 5, 5);
		layoutConst.gridx = 0;
		layoutConst.gridy = 0;
		matrixPanel.add(keyMatrixBox[0], layoutConst);
		
		layoutConst.insets = new Insets(0, 5, 5, 0);
		layoutConst.gridx = 1;
		layoutConst.gridy = 0;
		matrixPanel.add(keyMatrixBox[1], layoutConst);
		
		layoutConst.insets = new Insets(5, 0, 0, 5);
		layoutConst.gridx = 0;
		layoutConst.gridy = 1;
		matrixPanel.add(keyMatrixBox[2], layoutConst);
		
		layoutConst.insets = new Insets(5, 5, 0, 0);
		layoutConst.gridx = 1;
		layoutConst.gridy = 1;
		matrixPanel.add(keyMatrixBox[3], layoutConst);
		
		
		// Button Panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		
		layoutConst.fill = GridBagConstraints.HORIZONTAL;
		layoutConst.insets = new Insets(0, 0, 5, 0);
		layoutConst.gridx = 0;
		layoutConst.gridy = 0;
		buttonPanel.add(encodeButton, layoutConst);
		
		layoutConst.fill = GridBagConstraints.HORIZONTAL;
		layoutConst.insets = new Insets(5, 0, 0, 0);
		layoutConst.gridx = 0;
		layoutConst.gridy = 1;
		buttonPanel.add(decodeButton, layoutConst);
		
		
		// Prep Panel
		JPanel prepPanel = new JPanel();
		prepPanel.setLayout(new GridBagLayout());
		
		layoutConst.insets = new Insets(0, 0, 0, 45);
		layoutConst.gridx = 0;
		layoutConst.gridy = 0;
		prepPanel.add(buttonPanel, layoutConst);
		
		layoutConst.insets = new Insets(0, 45, 0, 10);
		layoutConst.gridx = 1;
		layoutConst.gridy = 0;
		prepPanel.add(matrixKeyLabel, layoutConst);
		
		layoutConst.insets = new Insets(0, 10, 0, 0);
		layoutConst.gridx = 2;
		layoutConst.gridy = 0;
		prepPanel.add(matrixPanel, layoutConst);
		
		
		// Add Panels And Main Components
		layoutConst.insets = new Insets(0, 0, 10, 0);
		layoutConst.gridx = 0;
		layoutConst.gridy = 0;
		add(prepPanel, layoutConst);
		
		layoutConst.insets = new Insets(10, 0, 0, 0);
		layoutConst.gridx = 0;
		layoutConst.gridy = 1;
		add(scrollPane, layoutConst);
		
	}
	
	
	public void actionPerformed(ActionEvent e) {
		
		JButton sourceEvent = (JButton) e.getSource();
		
		// When "Encode" Button Is Clicked
		if(sourceEvent == encodeButton) {
			encode();
		}
		
		// When "Decode" Button Is Clicked
		if(sourceEvent == decodeButton) {
			decode();
		}
		
	}
	
	
	public void getKeyMatrix() throws NumberFormatException, InvalidMatrixException {
		
		for(r = 0; r < 2; r++) {
			for(c = 0; c < 2; c++) {
				keyMatrix[r][c] = Double.parseDouble(keyMatrixBox[(r * 2) + c].getText());
			}
		}
		
		try {
			getInverseKeyMatrix();
		} catch (InvalidMatrixException e) {
			throw new InvalidMatrixException("Invalid Matrix");
		}
		
	}
	
	
	public void getInverseKeyMatrix() throws InvalidMatrixException {
		
		double determinant = ((keyMatrix[0][0] * keyMatrix[1][1]) - (keyMatrix[0][1] * keyMatrix[1][0]));
		
		if(0.00000005 > determinant && -0.00000005 < determinant) {
			throw new InvalidMatrixException("Invalid Matrix");
		} else {
			inverseKeyMatrix[0][0] = keyMatrix[1][1] * (1/determinant);
			inverseKeyMatrix[0][1] = keyMatrix[0][1] * -(1/determinant);
			inverseKeyMatrix[1][0] = keyMatrix[1][0] * -(1/determinant);
			inverseKeyMatrix[1][1] = keyMatrix[0][0] * (1/determinant);
		}
		
	}
	
	
	public double[][] multiplyMatrices(double[][] matrix1, double[][] matrix2) 
			throws CannotMultiplyMatrixException {
			
		if (matrix1[0].length != matrix2.length) {
			throw new CannotMultiplyMatrixException("Unable To Multiply Matrices");
		} else {
		
			double[][] multipliedMatrix = new double[matrix1.length][matrix2[0].length];
		
			for(r = 0; r < multipliedMatrix.length; r++) {
				for(c = 0; c < multipliedMatrix[0].length; c++) {
					
					double matrixNum = 0.0;
					
					for(i = 0; i < matrix1[r].length; i++) {
						matrixNum += matrix1[r][i] * matrix2[i][c];
					}
					
					multipliedMatrix[r][c] = matrixNum;
					
				}
			}
		
			return multipliedMatrix;
			
		}
		
	}
	
	
	public int getEncodingNumberRepresentation(char character) {
		
		int ascii = character;
		
		// Lower Case
		if(ascii >= 97 && ascii <= 122)
			return ascii - 96;
		
		// Upper Case
		else if(ascii >= 65 && ascii <= 90)
			return ascii - 38;
		
		// Digits
		else if(ascii >= 48 && ascii <= 57)
			return ascii + 5;
		
		// Symbols #1
		else if(ascii >= 32 && ascii <= 47)
			return ascii + 31;
		
		// Symbols #2
		else if(ascii >= 58 && ascii <= 64)
			return ascii + 21;
		
		// Symbols #3
		else if(ascii >= 91 && ascii <= 96)
			return ascii - 5;
		
		// Symbols #4
		else if(ascii >= 123 && ascii <= 126)
			return ascii - 31;
		
		return -1;
		
	}
	
	
	public double[][][] getEncodingNumberMatrix() 
			throws CannotMultiplyMatrixException{
		
		try {
			
			if(mainText.length() % 2 != 0)
				mainText += " ";
		
			double[][][] numMatrix = new double[mainText.length() / 2][2][1];
		
			for(j = 0; j < numMatrix.length; j++) {
				numMatrix[j][0][0] = getEncodingNumberRepresentation(mainText.charAt(j * 2));
				numMatrix[j][1][0] = getEncodingNumberRepresentation(mainText.charAt((j * 2) + 1));
				numMatrix[j] = multiplyMatrices(keyMatrix, numMatrix[j]);
			}
			
			return numMatrix;
			
		} catch (CannotMultiplyMatrixException e) {
			throw new CannotMultiplyMatrixException("Unable To Multiply Matrices");
		}
		
	}
	
	
	public void encode() {
		
		try {
			
			getKeyMatrix();
			mainText = textArea.getText();
			message = "";
			
			if(mainText.length() != 0) {
				
				double[][][] numMatrix = getEncodingNumberMatrix();
				
				for(i = 0; i < numMatrix.length; i++) {
					message += numMatrix[i][0][0] + " ";
					message += numMatrix[i][1][0];
					if(i != numMatrix.length - 1)
						message += " ";
				}
				
				textArea.setText(message);
				
			}
			
		} catch (NumberFormatException e) {
			messageAlert("Non-Numeric Matrix");
		} catch (InvalidMatrixException e) {
			messageAlert("Invalid Matrix");
		} catch (CannotMultiplyMatrixException e) {
			messageAlert("Cannot Multiply Matrix");
		}
		
	}
	
	
	public char getDecodingCharacterRepresentation(double num) {
		
		int asciiKinda = (int) (num + 0.5);
		if(asciiKinda < 1 || asciiKinda > 95)
			return ' ';
		else
			return characterArr[asciiKinda - 1];
		
	}
	
	
	public char[] getDecodingCharacterArray() 
			throws CannotMultiplyMatrixException, NumberFormatException{
			
		try {
		
			while(mainText.charAt(mainText.length() - 1) == ' ')
				mainText = mainText.substring(0, mainText.length() - 1);
				
			int spaceIndex = 0;
			for(i = 0; i < mainText.length(); i++) {
				if(mainText.indexOf(' ', i) != -1) {
					i = mainText.indexOf(' ', i) + 1;
					spaceIndex++;
				}
			}
			
			double[][][] numMatrix = new double[(spaceIndex + 1) / 2][2][1];
			
			spaceIndex = 0;
			for(j = 0; j < numMatrix.length; j++) {
				numMatrix[j][0][0] = Double.parseDouble(mainText.substring(spaceIndex, mainText.indexOf(' ', spaceIndex)));
				spaceIndex = mainText.indexOf(' ', spaceIndex) + 1;
				if(j != numMatrix.length - 1)
					numMatrix[j][1][0] = Double.parseDouble(mainText.substring(spaceIndex, mainText.indexOf(' ', spaceIndex)));
				else
					numMatrix[j][1][0] = Double.parseDouble(mainText.substring(spaceIndex));
				spaceIndex = mainText.indexOf(' ', spaceIndex) + 1;
				numMatrix[j] = multiplyMatrices(inverseKeyMatrix, numMatrix[j]);
			}
			
			char[] charArr = new char[numMatrix.length * 2];
			
			for(i = 0; i < numMatrix.length; i++) {
				charArr[i * 2] = getDecodingCharacterRepresentation(numMatrix[i][0][0]);
				charArr[i * 2 + 1] = getDecodingCharacterRepresentation(numMatrix[i][1][0]);
			}
			
			return charArr;
			
		} catch (CannotMultiplyMatrixException e) {
			throw new CannotMultiplyMatrixException("Unable to Multiply Matrices");
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Invalid Message");
		}
		
	}
	
	
	public void decode() {
		
		try {
			
			getKeyMatrix();
			mainText = textArea.getText();
			message = "";
			
			if(mainText.length() != 0) {
				
				char[] characterArray = getDecodingCharacterArray();
				
				for(char character : characterArray)
					message += character;
				
				textArea.setText(message);
				
			}
			
		} catch (NumberFormatException e) {
			if(e.getMessage().equals("Invalid Matrix"))
				messageAlert("Non-Numeric Matrix");
			else if(e.getMessage().equals("Invalid Message"))
				messageAlert("Cannot Decode Message");
		} catch (InvalidMatrixException e) {
			messageAlert("Invalid Matrix");
		} catch (CannotMultiplyMatrixException e) {
			messageAlert("Cannot Multiply Matrix");
		}
		
	}
	
	
	public void messageAlert(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
	
	
	public static void main (String[] args) {
		
		MatrixEncoder matrixEncoderFrame = new MatrixEncoder();
		
		matrixEncoderFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		matrixEncoderFrame.setSize(500, 500);
		matrixEncoderFrame.setResizable(false);
		matrixEncoderFrame.setVisible(true);
		
	}
	
}
