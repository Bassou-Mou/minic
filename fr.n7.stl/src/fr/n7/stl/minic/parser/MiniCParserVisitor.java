// Generated from C:/Users/Bassou Mouacha/Downloads/minic-2025-2026/fr.n7.stl/MiniCParser.g4 by ANTLR 4.13.2

package fr.n7.stl.minic.parser;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.IOException;
import fr.n7.stl.minic.ast.*;
import fr.n7.stl.minic.ast.expression.*;
import fr.n7.stl.minic.ast.expression.accessible.*;
import fr.n7.stl.minic.ast.expression.allocation.*;
import fr.n7.stl.minic.ast.expression.assignable.*;
import fr.n7.stl.minic.ast.expression.value.*;
import fr.n7.stl.minic.ast.instruction.*;
import fr.n7.stl.minic.ast.instruction.declaration.*;
import fr.n7.stl.minic.ast.scope.*;
import fr.n7.stl.minic.ast.type.*;
import fr.n7.stl.minic.ast.type.declaration.*;
import fr.n7.stl.util.*;
import fr.n7.stl.tam.ast.*;
import fr.n7.stl.tam.ast.impl.*;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MiniCParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MiniCParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code declarationProgramme}
	 * labeled alternative in {@link MiniCParser#programme}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationProgramme(MiniCParser.DeclarationProgrammeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniCParser#bloc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBloc(MiniCParser.BlocContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniCParser#parametres}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametres(MiniCParser.ParametresContext ctx);
	/**
	 * Visit a parse tree produced by the {@code declarationVariable}
	 * labeled alternative in {@link MiniCParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationVariable(MiniCParser.DeclarationVariableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code declarationConstante}
	 * labeled alternative in {@link MiniCParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationConstante(MiniCParser.DeclarationConstanteContext ctx);
	/**
	 * Visit a parse tree produced by the {@code declarationType}
	 * labeled alternative in {@link MiniCParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationType(MiniCParser.DeclarationTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code declarationFonction}
	 * labeled alternative in {@link MiniCParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationFonction(MiniCParser.DeclarationFonctionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code instructionDeclaration}
	 * labeled alternative in {@link MiniCParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstructionDeclaration(MiniCParser.InstructionDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code instructionAffectation}
	 * labeled alternative in {@link MiniCParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstructionAffectation(MiniCParser.InstructionAffectationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code instructionAffichage}
	 * labeled alternative in {@link MiniCParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstructionAffichage(MiniCParser.InstructionAffichageContext ctx);
	/**
	 * Visit a parse tree produced by the {@code instructionSiSinon}
	 * labeled alternative in {@link MiniCParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstructionSiSinon(MiniCParser.InstructionSiSinonContext ctx);
	/**
	 * Visit a parse tree produced by the {@code instructionSi}
	 * labeled alternative in {@link MiniCParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstructionSi(MiniCParser.InstructionSiContext ctx);
	/**
	 * Visit a parse tree produced by the {@code instructionTantQue}
	 * labeled alternative in {@link MiniCParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstructionTantQue(MiniCParser.InstructionTantQueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code instructionReturn}
	 * labeled alternative in {@link MiniCParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstructionReturn(MiniCParser.InstructionReturnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code instructionFonctionLocale}
	 * labeled alternative in {@link MiniCParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstructionFonctionLocale(MiniCParser.InstructionFonctionLocaleContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniCParser#atomique}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomique(MiniCParser.AtomiqueContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniCParser#champ}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChamp(MiniCParser.ChampContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniCParser#etiquettes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEtiquettes(MiniCParser.EtiquettesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code typeAtomic}
	 * labeled alternative in {@link MiniCParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeAtomic(MiniCParser.TypeAtomicContext ctx);
	/**
	 * Visit a parse tree produced by the {@code typeNamed}
	 * labeled alternative in {@link MiniCParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeNamed(MiniCParser.TypeNamedContext ctx);
	/**
	 * Visit a parse tree produced by the {@code typeCouple}
	 * labeled alternative in {@link MiniCParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeCouple(MiniCParser.TypeCoupleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code typeRecord}
	 * labeled alternative in {@link MiniCParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeRecord(MiniCParser.TypeRecordContext ctx);
	/**
	 * Visit a parse tree produced by the {@code typeEnum}
	 * labeled alternative in {@link MiniCParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeEnum(MiniCParser.TypeEnumContext ctx);
	/**
	 * Visit a parse tree produced by the {@code affectableArray}
	 * labeled alternative in {@link MiniCParser#affectable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAffectableArray(MiniCParser.AffectableArrayContext ctx);
	/**
	 * Visit a parse tree produced by the {@code affectableIdentifiant}
	 * labeled alternative in {@link MiniCParser#affectable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAffectableIdentifiant(MiniCParser.AffectableIdentifiantContext ctx);
	/**
	 * Visit a parse tree produced by the {@code affectableField}
	 * labeled alternative in {@link MiniCParser#affectable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAffectableField(MiniCParser.AffectableFieldContext ctx);
	/**
	 * Visit a parse tree produced by the {@code affectablePointer}
	 * labeled alternative in {@link MiniCParser#affectable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAffectablePointer(MiniCParser.AffectablePointerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code affectableConversion}
	 * labeled alternative in {@link MiniCParser#affectable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAffectableConversion(MiniCParser.AffectableConversionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniCParser#expressions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressions(MiniCParser.ExpressionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniCParser#arguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArguments(MiniCParser.ArgumentsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionCharacter}
	 * labeled alternative in {@link MiniCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionCharacter(MiniCParser.ExpressionCharacterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionAddress}
	 * labeled alternative in {@link MiniCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionAddress(MiniCParser.ExpressionAddressContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionOpposite}
	 * labeled alternative in {@link MiniCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionOpposite(MiniCParser.ExpressionOppositeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionParenthese}
	 * labeled alternative in {@link MiniCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionParenthese(MiniCParser.ExpressionParentheseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionSequence}
	 * labeled alternative in {@link MiniCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionSequence(MiniCParser.ExpressionSequenceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionNot}
	 * labeled alternative in {@link MiniCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionNot(MiniCParser.ExpressionNotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionConditional}
	 * labeled alternative in {@link MiniCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionConditional(MiniCParser.ExpressionConditionalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionAnd}
	 * labeled alternative in {@link MiniCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionAnd(MiniCParser.ExpressionAndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionArrayAcess}
	 * labeled alternative in {@link MiniCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionArrayAcess(MiniCParser.ExpressionArrayAcessContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionCouple}
	 * labeled alternative in {@link MiniCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionCouple(MiniCParser.ExpressionCoupleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionAccess}
	 * labeled alternative in {@link MiniCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionAccess(MiniCParser.ExpressionAccessContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionField}
	 * labeled alternative in {@link MiniCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionField(MiniCParser.ExpressionFieldContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionNull}
	 * labeled alternative in {@link MiniCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionNull(MiniCParser.ExpressionNullContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionPointerAccess}
	 * labeled alternative in {@link MiniCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionPointerAccess(MiniCParser.ExpressionPointerAccessContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionConversion}
	 * labeled alternative in {@link MiniCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionConversion(MiniCParser.ExpressionConversionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionEquality}
	 * labeled alternative in {@link MiniCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionEquality(MiniCParser.ExpressionEqualityContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionInequality}
	 * labeled alternative in {@link MiniCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionInequality(MiniCParser.ExpressionInequalityContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionFirst}
	 * labeled alternative in {@link MiniCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionFirst(MiniCParser.ExpressionFirstContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionFunctionCall}
	 * labeled alternative in {@link MiniCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionFunctionCall(MiniCParser.ExpressionFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionAdditive}
	 * labeled alternative in {@link MiniCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionAdditive(MiniCParser.ExpressionAdditiveContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionTrue}
	 * labeled alternative in {@link MiniCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionTrue(MiniCParser.ExpressionTrueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionMultiplicative}
	 * labeled alternative in {@link MiniCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionMultiplicative(MiniCParser.ExpressionMultiplicativeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionFalse}
	 * labeled alternative in {@link MiniCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionFalse(MiniCParser.ExpressionFalseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expresionFloat}
	 * labeled alternative in {@link MiniCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresionFloat(MiniCParser.ExpresionFloatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionSecond}
	 * labeled alternative in {@link MiniCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionSecond(MiniCParser.ExpressionSecondContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionArrayAllocation}
	 * labeled alternative in {@link MiniCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionArrayAllocation(MiniCParser.ExpressionArrayAllocationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionString}
	 * labeled alternative in {@link MiniCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionString(MiniCParser.ExpressionStringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionOr}
	 * labeled alternative in {@link MiniCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionOr(MiniCParser.ExpressionOrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionPointerAllocation}
	 * labeled alternative in {@link MiniCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionPointerAllocation(MiniCParser.ExpressionPointerAllocationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionInt}
	 * labeled alternative in {@link MiniCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionInt(MiniCParser.ExpressionIntContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniCParser#types}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypes(MiniCParser.TypesContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniCParser#identifiant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifiant(MiniCParser.IdentifiantContext ctx);
}