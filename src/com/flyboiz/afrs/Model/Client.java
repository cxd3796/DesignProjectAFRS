package com.flyboiz.afrs.Model;

import com.flyboiz.afrs.Controller.Commands.Query;

import java.util.Stack;

public class Client
{
	private Stack<Query> queryStack = new Stack<>();
	private Stack<Query> undoStack = new Stack<>();
	private Stack<Query> redoStack = new Stack<>();
	private int cid;

	public Client(int cid)
	{
		this.cid = cid;
	}


	public Query getLastQuery()
	{
		return queryStack.peek();
	}

	public Query getLastRedo()
	{
		return undoStack.peek();
	}

	public Query getLastUndo()
	{
		return redoStack.peek();
	}

	public void moveLastUndoToRedo()
	{
		Query query = undoStack.pop();
		redoStack.push(query);
	}

	public void moveLastRedoToUndo()
	{
		Query query = redoStack.pop();
		undoStack.push(query);
	}
}
