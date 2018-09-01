package com.abubusoft.kripton.processor.core.reflect;

import javax.lang.model.type.TypeMirror;

import com.sun.source.tree.AnnotationTree;
import com.sun.source.tree.AssignmentTree;
import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.IdentifierTree;
import com.sun.source.tree.MemberSelectTree;
import com.sun.source.util.TreePathScanner;

public class AnnotationVisitor extends TreePathScanner<TypeMirror, Void> {
//    @Override
//    public TypeMirror visitAnnotation(AnnotationTree node, Void p) {
//        for (ExpressionTree expressionTree : node.getArguments()) {
//            if (!(expressionTree instanceof AssignmentTree)) {
//                continue;
//            }
//
//            AssignmentTree assignmentTree = (AssignmentTree)expressionTree;
//            ExpressionTree variable = assignmentTree.getVariable();
//            if (!(variable instanceof IdentifierTree) || !((IdentifierTree)variable).getName().contentEquals("rule")) {
//                continue;
//            }
//
//            return scan(expressionTree, p);
//        }
//
//        return null;
//    }

    @Override
    public TypeMirror visitAssignment(AssignmentTree at, Void p) {
        return scan(at.getExpression(), p);
    }

//    @Override
//    public TypeMirror visitMemberSelect(MemberSelectTree mst, Void p) {
//        return scan(mst.getExpression(), p);
//    }

}