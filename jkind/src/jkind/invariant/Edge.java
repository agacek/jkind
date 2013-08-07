package jkind.invariant;

import jkind.sexp.Cons;
import jkind.sexp.Sexp;
import jkind.solvers.Lambda;
import jkind.util.SexpUtil;

public class Edge {
	final public Node source;
	final public Node destination;

	public Edge(Node source, Node destination) {
		this.source = source;
		this.destination = destination;
	}

	public Invariant toInvariant(boolean pure) {
		Candidate sRep = source.getRepresentative();
		Candidate dRep = destination.getRepresentative();

		String text = sRep + " => " + dRep;
		Sexp body = new Cons("=>", sRep.index(SexpUtil.I, pure), dRep.index(SexpUtil.I, pure));
		Lambda lambda = new Lambda(SexpUtil.I, body);
		
		return new Invariant(lambda, text);
	}
	
	@Override
	public String toString() {
		return source + " -> " + destination;
	}
}