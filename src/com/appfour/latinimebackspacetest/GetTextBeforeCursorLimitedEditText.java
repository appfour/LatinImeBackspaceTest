package com.appfour.latinimebackspacetest;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.CorrectionInfo;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;

public class GetTextBeforeCursorLimitedEditText extends EditText {

	private static class GetTextBeforeCursorLimitedInputConnectionWrapper
			extends BaseInputConnection {

		private InputConnection ic;

		public GetTextBeforeCursorLimitedInputConnectionWrapper(View view, InputConnection ic) {
			super(view, true);
			this.ic = ic;
		}

		@Override
		public boolean beginBatchEdit() {
			return ic.beginBatchEdit();
		}

		@Override
		public boolean clearMetaKeyStates(int states) {
			return ic.clearMetaKeyStates(states);
		}

		@Override
		public boolean commitCompletion(CompletionInfo text) {
			return ic.commitCompletion(text);
		}

		@Override
		public boolean commitCorrection(CorrectionInfo correctionInfo) {
			return ic.commitCorrection(correctionInfo);
		}

		@Override
		public boolean commitText(CharSequence text, int newCursorPosition) {
			return ic.commitText(text, newCursorPosition);
		}

		@Override
		public boolean deleteSurroundingText(int beforeLength, int afterLength) {
			return ic.deleteSurroundingText(beforeLength, afterLength);
		}

		@Override
		public boolean endBatchEdit() {
			return ic.endBatchEdit();
		}

		@Override
		public boolean finishComposingText() {
			return ic.finishComposingText();
		}

		@Override
		public int getCursorCapsMode(int reqModes) {
			return ic.getCursorCapsMode(reqModes);
		}

		@Override
		public ExtractedText getExtractedText(ExtractedTextRequest request,
				int flags) {
			return ic.getExtractedText(request, flags);
		}

		@Override
		public CharSequence getSelectedText(int flags) {
			return ic.getSelectedText(flags);
		}

		@Override
		public CharSequence getTextAfterCursor(int n, int flags) {
			return ic.getTextAfterCursor(n, flags);
		}

		@Override
		public CharSequence getTextBeforeCursor(int n, int flags) {
			CharSequence res = ic.getTextBeforeCursor(n, flags);
			if (res != null)
			{
				int len = Math.min(1, res.length());
				res = res.subSequence(res.length() - len, res.length());
			}
			return res;
		}

		@Override
		public boolean performContextMenuAction(int id) {
			return ic.performContextMenuAction(id);
		}

		@Override
		public boolean performEditorAction(int editorAction) {
			return ic.performEditorAction(editorAction);
		}

		@Override
		public boolean performPrivateCommand(String action, Bundle data) {
			return ic.performPrivateCommand(action, data);
		}

		@Override
		public boolean reportFullscreenMode(boolean enabled) {
			return ic.reportFullscreenMode(enabled);
		}

		@Override
		public boolean sendKeyEvent(KeyEvent event) {
			return ic.sendKeyEvent(event);
		}

		@Override
		public boolean setComposingRegion(int start, int end) {
			return ic.setComposingRegion(start, end);
		}

		@Override
		public boolean setComposingText(CharSequence text, int newCursorPosition) {
			return ic.setComposingText(text, newCursorPosition);
		}

		@Override
		public boolean setSelection(int start, int end) {
			return ic.setSelection(start, end);
		}

	}

	public GetTextBeforeCursorLimitedEditText(Context context) {
		super(context);
	}

	public GetTextBeforeCursorLimitedEditText(Context context,
			AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public GetTextBeforeCursorLimitedEditText(Context context,
			AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
		InputConnection ic = super.onCreateInputConnection(outAttrs);
		if (ic != null) {
			ic = new GetTextBeforeCursorLimitedInputConnectionWrapper(this, ic);
		}
		return ic;
	}
}
