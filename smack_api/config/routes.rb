Rails.application.routes.draw do
  # For details on the DSL available within this file, see http://guides.rubyonrails.org/routing.html
  resources :smacks
  get '/smacks/:id/', to: 'smacks#index'
  get '/toggle/smacks/:id/', to: 'smacks#update'
end
