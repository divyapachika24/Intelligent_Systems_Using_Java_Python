package astaralgorithm;

import java.util.Arrays;

public class Node {
	int fcost,gcost,hcost;
	int stateId;
	int parentId;
	int state[][]=new int[3][3];
	public int[][] getState() {
		return state;
	}
	public void setState(int[][] state) {
		this.state = state;
	}
	public int getFcost() {
		return fcost;
	}
	public void setFcost(int fcost) {
		this.fcost = fcost;
	}
	public int getGcost() {
		return gcost;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + fcost;
		result = prime * result + gcost;
		result = prime * result + hcost;
		result = prime * result + Arrays.deepHashCode(state);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (!EqualArrays.checkequal(state, other.state))
			return false;
		return true;
	}
	public void setGcost(int gcost) {
		this.gcost = gcost;
	}
	public int getHcost() {
		return hcost;
	}
	public void setHcost(int hcost) {
		this.hcost = hcost;
	}
	public int getStateId() {
		return stateId;
	}
	public void setStateId(int stateId) {
		this.stateId = stateId;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	@Override
	public String toString() {
		return "Node [fcost=" + fcost + ", gcost=" + gcost + ", hcost=" + hcost + ", stateId=" + stateId + ", parentId="
				+ parentId + ", state=" + Arrays.toString(state) + "]";
	}
	
}
