/**
 * Rashedul Khan
 * 108921821
 * CSE 214 (2)
 * TA: Rathish Das
 */

import java.util.*;

public class PostfixEvaluator {
	public static final char ADD = '+';
	public static final char SUB = '-';
	public static final char DIV = '/';
	public static final char MULTI = '*';
	private static boolean flag = false;
	public static void main(String[] args) {
		
		InFixToPostfixConverter conv = new InFixToPostfixConverter();
		PostfixEvaluator eval = new PostfixEvaluator();
		Scanner stdin = new Scanner(System.in);
		System.out.print("Infix Expression: ");
		String input = stdin.nextLine();
		
		while (!(input.equals("q") || input.equals("Q"))) {
			try {
				String post = conv.convert(input.toCharArray());	//	Infix to Postfix
				flag = true;
				int rl = 0;
				try{
					flag = false;
					rl = eval.evaluate(post.toCharArray());
				} catch (EmptyStackException ex) {
					flag = true;
					System.out.println("INVALID FORMAT");
				}
				if (!post.contains("("))
					System.out.println("Postfix: " + post);	//	Infix to Postfix
				if (!flag) {
					System.out.println("Result: " + rl);	//	Postfix evaluator
				}
				
			} catch (IllegalArgumentException ex) {
				System.out.println("Invalid Format. Invalid number of operators and Operands");
			} catch (EmptyStackException ex) {
				System.out.println("Invalid Format.");
			} catch (ArithmeticException ex) {
				System.out.println("Invalid Format. Divison by 0");
			}
			
			System.out.print("Infix Expression: ");
			input = stdin.nextLine();
		}	
	}
	
	public int evaluate (char[] postfix) {
		Stack<Integer> numStack = new Stack<Integer>();
		int result = 0;
		for (int i=0;i<postfix.length;i++){
			
			char token = postfix[i];
			if (token == MULTI) {
				int operand2 = numStack.pop();	// convert char to int.
				int operand1 = numStack.pop();
				result = operand1 * operand2;
				numStack.push(result);
			}
			else if (token == DIV) {
				int operand2 = numStack.pop();	// convert char to int.
				int operand1 = numStack.pop();
				result = operand1 / operand2;
				numStack.push(result);
			}
			else if (token == SUB) {
				int operand2 = numStack.pop();	// convert char to int.
				int operand1 = numStack.pop();
				result = operand1 - operand2;
				numStack.push(result);
			}
			else if (token == ADD) {
				int operand2 = numStack.pop();	// convert char to int.
				int operand1 = numStack.pop();
				result = operand1 + operand2 ;
				numStack.push(result);
			}
			else {
				try {
					numStack.push(Integer.parseInt(token+""));
				} catch(NumberFormatException ex) {
					System.out.println("Invalid format!!");
					flag = true;
					break;
					} 				
			}
		}
		
		return result;
	}
	
		
}
