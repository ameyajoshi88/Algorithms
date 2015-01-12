package com.expression;

import java.util.Stack;

public class Expression {
	
	private static String infixToPostfix(String infix) {
		StringBuilder sb = new StringBuilder("");
		char[] tokens = infix.toCharArray();
		Stack<Character> stack = new Stack<Character>();
		
		for(char token:tokens) {
			if(isOperand(token)) {
				sb.append(token);
			} else if(isOperator(token)) {
				while(!stack.isEmpty() && stack.peek() != '(' && hasHigherPrecedence(stack.peek(), token)) {
					sb.append(stack.pop());
				}
				stack.push(token);
			} else if(token == '(') {
				stack.push(token);
			} else if(token == ')') {
				while(!stack.isEmpty() && stack.peek() != '(') {
					sb.append(stack.pop());
				}
				stack.pop();
			}
		}
		
		
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		
		return sb.toString();
	}
	
	private static float evaluatePostfix(String postfix) {
		float result = 0;
		char[] tokens = postfix.toCharArray();
		Stack<Float> stack = new Stack<Float>();
		
		for(char token:tokens) {
			if(isOperand(token)) {
				stack.push((float)(token - '0'));
			} else if(isOperator(token)) {
				result = operate(token, stack.pop(), stack.pop());
				stack.push(result);
			}
		}
		
		return stack.pop();
	}
	
	private static boolean isOperand(char c) {
		if(c >= '0' && c <= '9') {
			return true;
		}
		
		return false;
	}
	
	private static boolean isOperator(char c) {
		if(c == '+' || c == '-' || c == '*' || c == '/') {
			return true;
		}
		
		return false;
	}
	
	private static boolean hasHigherPrecedence(char op1, char op2) {
		if((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
			return true;
		}
		
		return false;
	}
	
	private static float operate(char operator, float op1, float op2) {
		float result = 0;
		switch(operator) {
		case '+':
			result = op1 + op2;
			break;
		case '-':
			result = op1 - op2;
			break;
		case '*':
			result = op1 * op2;
			break;
		case '/':
			result = op1 / op2;
			break;
		}
		
		return result;
	}
	
	public static void main(String args[]) {
		// without parentheses
		String infix = "2+3*5";
		String postfix = infixToPostfix(infix);
		System.out.println("infix to postfix="+postfix);
		System.out.println("result="+evaluatePostfix(postfix));
		
		// with parentheses
		infix = "(2+3)*5";
		postfix = infixToPostfix(infix);
		System.out.println("infix to postfix="+postfix);
		System.out.println("result="+evaluatePostfix(postfix));
	}
}
