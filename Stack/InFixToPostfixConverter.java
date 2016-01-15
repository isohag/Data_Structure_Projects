/**
 * Rashedul Khan
 * 108921821
 * CSE 214 (2)
 * TA: Rathish Das
 */

public class InFixToPostfixConverter {
	
	Stack<Character> opStack = new Stack<Character>();	//	Operation (*/+-)
	
	private int prec(char token){
		if (token == '$')
			return 0;
		else if (token == '(')
			return 1;
		else if (token == PostfixEvaluator.ADD || token == PostfixEvaluator.SUB)
			return 2;
		else if (token == PostfixEvaluator.MULTI || token == PostfixEvaluator.DIV)
			return 3;
		else
			return -1;
	}
	
	public String convert (char[] infix) {
		String P = "";
		opStack.push('$');
		char topOp;
		int op = 0;
		int nd = 0;
		for (int i=0; i<infix.length; i++){
			// Token
			char token = infix[i];	
			// Operator
			if (token == PostfixEvaluator.MULTI || token == PostfixEvaluator.DIV 
					|| token == PostfixEvaluator.ADD || token == PostfixEvaluator.SUB){
				topOp = opStack.peek();
				while (prec(topOp) >= prec(token)) {
					P += opStack.pop();
					topOp = opStack.peek();
				}
				opStack.push(token);
				op++;
			}
			
			else if (token == '(')
				opStack.push(token);
			
			else if (token == ')'){
				topOp = opStack.pop();
				while (topOp != '(') {
					P += topOp;
					topOp = opStack.pop();
				}
			}
			
			// Operand	
			else {	
				P += token;
				nd++;
			}
		}
		
		topOp = opStack.pop();
		while (topOp != '$'){
			P += topOp;
			topOp = opStack.pop();
		}
		if (!(op+1==nd)) {
			throw new IllegalArgumentException();
		}
		return P;
	}
}
