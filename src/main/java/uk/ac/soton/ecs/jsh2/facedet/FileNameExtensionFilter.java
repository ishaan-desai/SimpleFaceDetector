package uk.ac.soton.ecs.jsh2.facedet;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FileNameExtensionFilter extends FileFilter {
	String description;

	String extensions[];

	public FileNameExtensionFilter(String description, String extension) {
		this(description, new String[] { extension });
	}

	public FileNameExtensionFilter(String description, String... extensions) {
		if (description == null) {
			this.description = extensions[0];
		} else {
			this.description = description;
		}
		this.extensions = extensions.clone();

		toLower(this.extensions);
	}

	private void toLower(String array[]) {
		for (int i = 0, n = array.length; i < n; i++) {
			array[i] = array[i].toLowerCase();
		}
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public boolean accept(File file) {
		if (file.isDirectory()) {
			return true;
		} else {
			final String path = file.getAbsolutePath().toLowerCase();
			for (int i = 0, n = extensions.length; i < n; i++) {
				final String extension = extensions[i];
				if ((path.endsWith(extension) && (path.charAt(path.length() - extension.length() - 1)) == '.')) {
					return true;
				}
			}
		}
		return false;
	}

	public String getDefaultExtension() {
		return extensions[0];
	}
}
