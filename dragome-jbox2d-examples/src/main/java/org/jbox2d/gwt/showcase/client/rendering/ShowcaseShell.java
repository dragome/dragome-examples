package org.jbox2d.gwt.showcase.client.rendering;

import com.dragome.model.VisualPanelImpl;

public class ShowcaseShell extends VisualPanelImpl
{
/*
	interface ShowcaseShellUiBinder extends UiBinder<Widget, ShowcaseShell>
	{
	}

	private static ShowcaseShellUiBinder uiBinder= GWT.create(ShowcaseShellUiBinder.class);

	private EventBus eventBus;
	private ExamplesTreeModel treeViewModel;

	@UiField
	SimplePanel contentPanel;

	@UiField
	Button restartButton;

	@UiField(provided= true)
	CellTree mainMenu;

	private ExampleView exampleView;

	public ShowcaseShell(EventBus eventBus)
	{
		this.eventBus= eventBus;

		// Create the cell tree.
		treeViewModel= ExamplesTreeModel.create(eventBus);
		mainMenu= new CellTree(treeViewModel, null);
		mainMenu.setAnimationEnabled(true);
		mainMenu.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.DISABLED);

		// Initialize the ui binder.
		initWidget(uiBinder.createAndBindUi(this));

		exampleView= new ExampleView(eventBus);
		contentPanel.add(exampleView);

		// select first example
		TreeNode firstCategoryNode= mainMenu.getRootTreeNode().setChildOpen(0, true);
		treeViewModel.getSelectionModel().setSelected((BaseExample) firstCategoryNode.getChildValue(0), true);
	}

	public CanvasDebugDraw getCameraRenderer()
	{
		return exampleView.getCameraRenderer();
	}

	@UiHandler("restartButton")
	public void onRestartButtonClick(ClickEvent click)
	{
		BaseExample be= treeViewModel.getSelectionModel().getSelectedObject();
		if (be != null)
		{
			eventBus.fireEvent(new StartExampleEvent(be));
		}
	}
	
	*/
}
